package com.fkhmarketplace.pricing.poc;

import static org.kie.internal.command.CommandFactory.newBatchExecution;
import static org.kie.internal.command.CommandFactory.newEnableAuditLog;
import static org.kie.internal.command.CommandFactory.newFireAllRules;
import static org.kie.internal.command.CommandFactory.newInsert;
import com.fkhmarketplace.pricing.poc.pojo.DiscountBasePrice;
import com.fkhmarketplace.pricing.poc.pojo.DiscountInputSignals;
import com.fkhmarketplace.pricing.poc.pojo.Lot;
import com.fkhmarketplace.pricing.poc.pojo.Message;
import com.fkhmarketplace.pricing.poc.pojo.Product;
import com.fkhmarketplace.pricing.poc.pojo.Seller;
import com.fkhmarketplace.pricing.poc.rules.TrackingAgendaEventListener;
import java.util.ArrayList;
import java.util.List;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static KnowledgeBaseHandler knowledgeBaseHandler = new KnowledgeBaseHandler();

    public static void main(String[] args) {
        System.out.println("drools say whaaaat!?");

        staticDrlRuleEvaluation("matched!");
        staticDrlRuleEvaluation("another match!");
        staticDrlRuleEvaluation("no match");
//
//        addNewRuleDrl("matched!");
//        addNewRuleDrl("another match!");
//        addNewRuleDrl("no match");
//
//        addExcelDecisionTableAsDrls("matched!");
//        addExcelDecisionTableAsDrls("another match!");
//        addExcelDecisionTableAsDrls("no match");

//        discountRuleEvaluation();
    }

    private static void discountRuleEvaluation() {
        KieSession kieSession = knowledgeBaseHandler.getSession();
        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        System.out.println("==");

        System.out.println("matchesToString => " + agendaEventListener.matchsToString());

        Product product = new Product("glaxosmithkline", "Rx");
        Seller seller = new Seller("seller_location_123");
        Lot lot = new Lot(20.0D);

        DiscountInputSignals discountInputSignals = new DiscountInputSignals(product, seller, lot);
        DiscountBasePrice discountBasePrice = new DiscountBasePrice();

        List<Command> commandList = new ArrayList<>();
        commandList.add(newInsert(discountInputSignals, "inputData"));
        commandList.add(newInsert(discountBasePrice, "successEvaluation"));
        commandList.add(newFireAllRules());

        ExecutionResults executionResults = kieSession.execute(newBatchExecution(commandList));

        System.out.println("rule result: " + executionResults.getValue("successEvaluation"));
        System.out.println("matchesToString => " + agendaEventListener.matchsToString());
    }

    private static void addExcelDecisionTableAsDrls(String msg) {
        String excelFile = "com/fkhmarketplace/pricing/poc/excelDecisionTables/decision_rules.xlsx";

        KieSession kieSession = knowledgeBaseHandler.addDrlFromExcelAtRuntime(excelFile);
        evaluate(msg, kieSession);
    }

    private static void addNewRuleDrl(String msg) {
        String drl = createDrl();

        KieSession kieSession = knowledgeBaseHandler.addDrlAtRuntime(drl);
        evaluate(msg, kieSession);
    }

    private static void staticDrlRuleEvaluation(String msg) {
        KieSession kieSession = knowledgeBaseHandler.getSession();

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);
//        System.out.println("matchesToString => " + agendaEventListener.matchsToString());

        evaluate(msg, kieSession);

//        System.out.println("matchesToString => " + agendaEventListener.matchsToString());
        kieSession.dispose();

    }

    private static void evaluate(String msg, KieSession kieSession) {
        System.out.println("==");

        Message message = new Message();
        message.setStatus(msg);

        List<Command> commandList = new ArrayList<>();
        commandList.add(newEnableAuditLog("auditlog.log"));
        commandList.add(newInsert(message, "msg"));
        commandList.add(newFireAllRules());

        ExecutionResults executionResults = kieSession.execute(newBatchExecution(commandList));
        System.out.println("rule result: " + executionResults.getValue("msg"));
    }

    private static String createDrl() {
        return new StringBuilder()
                .append("package com.fkhmarketplace.pricing.poc\n")
                .append("\n")
                .append("import com.fkhmarketplace.pricing.poc.pojo.Message;\n")
                .append("\n")
                .append("rule \"a runtime sample rule\"\n")
                .append("when\n")
                .append("    m: Message(status == \"matched!\")\n")
                .append("then\n")
                .append("    System.out.println(\"c[_] runtime rule \" + m.getStatus());\n")
                .append("end\n")
                .append("\n")
                .append("\n")
                .append("rule \"another runtime sample rule\"\n")
                .append("when\n")
                .append("    m: Message(status == \"another match!\")\n")
                .append("then\n")
                .append("    System.out.println(\"runtime drools says \" + m.getStatus() + \" =^..^=\");\n")
                .append("end\n")
                .toString();
    }
}
