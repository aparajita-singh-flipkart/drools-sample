package com.fkhmarketplace.pricing.poc;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.drools.core.builder.conf.impl.DecisionTableConfigurationImpl;
import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.io.ResourceFactory;

public class KnowledgeBaseHandler {

    public KieSession getSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        return kieContainer.getKieBase("KBase1").newKieSession();
    }

    public KieSession addDrlAtRuntime(String drls) {
        KieServices kieServices = KieServices.Factory.get();

        final ReleaseId releaseId1 = kieServices.newReleaseId("com.fkhmarketplace.pricing", "poc", "1.0.0");
        final KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        final KieBuilder builder = KieServices.Factory.get()
                .newKieBuilder(getKieFileSystemWithKieModule(kieModuleModel, releaseId1, getResourceFromDrl(drls)));
        final KieModule kieModule = builder.getKieModule();
        kieServices.getRepository().addKieModule(kieModule);
        KieContainer kieContainer = kieServices.newKieContainer(releaseId1);
        return kieContainer.newKieSession();
    }

    public KieSession addDrlFromExcelAtRuntime(String excelPath) {
        KieServices kieServices = KieServices.Factory.get();
        Resource dt = ResourceFactory.newClassPathResource(excelPath, getClass());

        DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();
        DecisionTableConfiguration decisionTableConfiguration = new DecisionTableConfigurationImpl();
        decisionTableConfiguration.setInputType(DecisionTableInputType.XLSX);
        String drl = decisionTableProvider.loadFromResource(dt, decisionTableConfiguration);
        System.out.println("EXCEL RULE => " + drl);

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem().write(dt);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieRepository kieRepository = kieServices.getRepository();
        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
        return kieContainer.newKieSession();
    }

    public static List<Resource> getResourcesFromDrls(final String... drls) {
        final List<Resource> resources = new ArrayList<>();
        for (int i = 0; i < drls.length; i++) {
            // This null check can be used to skip unwanted filenames.
            if (drls[i] != null) {
                final Resource drlResource =
                        KieServices.Factory.get().getResources().newReaderResource(new StringReader(drls[i]));
                drlResource.setSourcePath(Constants.RESOURCES_FOLDER + "rules-" + (i + 1) + ".drl");
                resources.add(drlResource);
            }
        }
        return resources;
    }

    public static Resource getResourceFromDrl(final String drls) {
        if (drls != null) {
            final Resource drlResource =
                    KieServices.Factory.get().getResources().newReaderResource(new StringReader(drls));
            drlResource.setSourcePath(Constants.RESOURCES_FOLDER + "rules" + ".drl");
            return drlResource;
        }

        return null;
    }

    public static KieFileSystem getKieFileSystemWithKieModule(final KieModuleModel kieModuleModel,
                                                              final ReleaseId releaseId, final Resource... resources) {
        final KieFileSystem fileSystem = KieServices.Factory.get().newKieFileSystem();
        fileSystem.generateAndWritePomXML(releaseId);
        for (final Resource resource : resources) {
            fileSystem.write(resource);
        }
        fileSystem.writeKModuleXML(kieModuleModel.toXML());
        return fileSystem;
    }
}
