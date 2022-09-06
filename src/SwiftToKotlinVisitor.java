import generatedantlr.Swift3BaseVisitor;
import generatedantlr.Swift3Parser;

public class SwiftToKotlinVisitor extends Swift3BaseVisitor<String> {
    @Override
    public String visitTop_level(Swift3Parser.Top_levelContext ctx) {
       String kotlinCode = visitStatements(ctx.statements());
       return kotlinCode;
    }

    @Override
    public String visitStatements(Swift3Parser.StatementsContext ctx) {
        String kotlinStatements = visitStatements_impl(ctx.statements_impl());
        return kotlinStatements;
    }


    @Override
    public String visitStatements_impl(Swift3Parser.Statements_implContext ctx) {
       String kotlinStatementImplementation = visitStatement(ctx.statement());
       return kotlinStatementImplementation;
    }

    @Override
    public String visitStatement(Swift3Parser.StatementContext ctx) {
        String kotlinStatement = visitDeclaration(ctx.declaration());
        return kotlinStatement;
    }


    @Override
    public String visitControl_transfer_statement(Swift3Parser.Control_transfer_statementContext ctx) {
        return "";
    }

    @Override
    public String visitReturn_statement(Swift3Parser.Return_statementContext ctx) {
        return "";
    }

    @Override
    public String visitLoop_statement(Swift3Parser.Loop_statementContext ctx) {
        return "";
    }

    @Override
    public String visitFor_in_statement(Swift3Parser.For_in_statementContext ctx) {
        return "";
    }

    @Override
    public String visitRepeat_while_statement(Swift3Parser.Repeat_while_statementContext ctx) {
        return "";
    }

    @Override
    public String visitCode_block(Swift3Parser.Code_blockContext ctx) {
        return visitStatements(ctx.statements());
    }

    @Override
    public String visitWhile_statement(Swift3Parser.While_statementContext ctx) {
        return "";
    }

    @Override
    public String visitDeclaration(Swift3Parser.DeclarationContext ctx) {
        String kotlinDeclaration = "";
        if (ctx.variable_declaration() != null)
        {
            kotlinDeclaration = visitVariable_declaration(ctx.variable_declaration());
        }
        else if (ctx.function_declaration() != null) {
            kotlinDeclaration = visitFunction_declaration(ctx.function_declaration());
        }
        return kotlinDeclaration;
    }


    @Override
    public String visitStruct_declaration(Swift3Parser.Struct_declarationContext ctx) {
        return "";
    }

    @Override
    public String visitStruct_body(Swift3Parser.Struct_bodyContext ctx) {
        return "";
    }

    @Override
    public String visitStruct_member(Swift3Parser.Struct_memberContext ctx) {
        return "";
    }

    @Override
    public String visitInitializer_declaration(Swift3Parser.Initializer_declarationContext ctx) {
        return "";
    }



    @Override
    public String visitParameter_list(Swift3Parser.Parameter_listContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitFunction_declaration(Swift3Parser.Function_declarationContext ctx) {
        String kotlinFunctionDeclaration = "fun ";
        kotlinFunctionDeclaration += ctx.function_name().declaration_identifier().getText();
        kotlinFunctionDeclaration += visitFunction_signature(ctx.function_signature());
        kotlinFunctionDeclaration += " {\n\t" + visitCode_block(ctx.function_body().code_block()) + "\n}";

        return kotlinFunctionDeclaration;
    }

    @Override
    public String visitFunction_signature(Swift3Parser.Function_signatureContext ctx) {
        String kotlinFunctionSignature = "(" + visitParameter_list(ctx.parameter_clause().parameter_list()) + ") : ";
        kotlinFunctionSignature += ctx.function_result().getChild(1).getText();
        return kotlinFunctionSignature;
    }

    @Override
    public String visitConstant_declaration(Swift3Parser.Constant_declarationContext ctx) {
        return "";
    }

    @Override
    public String visitClass_declaration(Swift3Parser.Class_declarationContext ctx) {
        return "";
    }

    @Override
    public String visitClass_body(Swift3Parser.Class_bodyContext ctx) {
        return "";
    }

    @Override
    public String visitBranch_statement(Swift3Parser.Branch_statementContext ctx) {
        return "";
    }
    @Override
    public String visitGuard_statement(Swift3Parser.Guard_statementContext ctx){
        return "";
    }

    @Override
    public String visitIf_statement(Swift3Parser.If_statementContext ctx) {
        return "";
    }
    @Override
    public String visitCondition_list(Swift3Parser.Condition_listContext ctx) {
        return "";
    }


    @Override
    public String visitCondition(Swift3Parser.ConditionContext ctx) {
        return "";
    }

    @Override
    public String visitElse_clause(Swift3Parser.Else_clauseContext ctx) {
        return "";
    }
    @Override
    public String visitSwitch_statement(Swift3Parser.Switch_statementContext ctx) {
        return "";
    }

    @Override
    public String visitSwitch_cases(Swift3Parser.Switch_casesContext ctx) {
        return "";
    }

    @Override
    public String visitSwitch_case(Swift3Parser.Switch_caseContext ctx) {
        return "";
    }

    @Override
    public String visitVariable_declaration(Swift3Parser.Variable_declarationContext ctx) {
        String kotlinVariableDeclaration = visitPattern_initializer_list(ctx.pattern_initializer_list());
        return "var " + kotlinVariableDeclaration;
    }

    @Override
    public String visitPattern_initializer_list(Swift3Parser.Pattern_initializer_listContext ctx) {
        String kotlinVariableInitializerList = "";
        for (int i = 0; i < ctx.pattern_initializer().size(); i++) {
            kotlinVariableInitializerList += visitPattern_initializer(ctx.pattern_initializer(i));
        }
        return kotlinVariableInitializerList;
    }

    @Override
    public String visitPattern_initializer(Swift3Parser.Pattern_initializerContext ctx) {
        String kotlinPatternInitializer = visitPattern(ctx.pattern());
        if (ctx.initializer() == null)
            return kotlinPatternInitializer;

        return kotlinPatternInitializer + visitInitializer(ctx.initializer());
    }

    @Override
    public String visitPattern(Swift3Parser.PatternContext ctx) {
        String kotlinPattern = visitIdentifier_pattern(ctx.identifier_pattern());
        return kotlinPattern;
    }


    @Override
    public String visitExpression_pattern(Swift3Parser.Expression_patternContext ctx) {
        return "";
    }

    @Override
    public String visitType_annotation(Swift3Parser.Type_annotationContext ctx) {
        return "";
    }

    @Override
    public String visitIdentifier_pattern(Swift3Parser.Identifier_patternContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitInitializer(Swift3Parser.InitializerContext ctx) {
        String kotlinInitializer = visitExpression(ctx.expression());
        return " = " + kotlinInitializer;
    }

    @Override
    public String visitExpression(Swift3Parser.ExpressionContext ctx) {
        String kotlinPrefixExpression = visitPrefix_expression(ctx.prefix_expression());
        return kotlinPrefixExpression;
    }

    @Override
    public String visitBinary_expressions(Swift3Parser.Binary_expressionsContext ctx) {
        return "";
    }

    @Override
    public String visitBinary_expression(Swift3Parser.Binary_expressionContext ctx) {
        return "";
    }

    @Override
    public String visitPrefix_expression(Swift3Parser.Prefix_expressionContext ctx) {
        Swift3Parser.PrimaryContext pctx = (Swift3Parser.PrimaryContext) ctx.postfix_expression();
        String kotlinPrefixExpression = visitPrimary(pctx);

        return kotlinPrefixExpression;
    }

    @Override
    public String visitExplicit_member_expression2(Swift3Parser.Explicit_member_expression2Context ctx) {
        return "";
    }

    @Override
    public String visitPostfix_operation(Swift3Parser.Postfix_operationContext ctx) {
        return "";
    }

    @Override
    public String visitPrimary(Swift3Parser.PrimaryContext ctx) {
        String kotlinPrimary = visitLiteral_expression(ctx.primary_expression().literal_expression());
        return kotlinPrimary;
    }

    @Override
    public String visitPrimary_expression(Swift3Parser.Primary_expressionContext ctx) {
        return "";
    }

    @Override
    public String visitClosure_expression(Swift3Parser.Closure_expressionContext ctx) {
        return "";
    }

    @Override
    public String visitLiteral_expression(Swift3Parser.Literal_expressionContext ctx) {
        return ctx.literal().getText();
    }
    @Override
    public String visitParenthesized_expression(Swift3Parser.Parenthesized_expressionContext ctx) {
        return "";
    }

    @Override
    public String visitPostfix_operator(Swift3Parser.Postfix_operatorContext ctx) {
        return "";
    }

    @Override
    public String visitFunction_call_argument_clause(Swift3Parser.Function_call_argument_clauseContext ctx) {
        return "";
    }

    @Override
    public String visitFunction_call_argument_list(Swift3Parser.Function_call_argument_listContext ctx) {
        return "";
    }

    @Override
    public String visitFunction_call_argument(Swift3Parser.Function_call_argumentContext ctx) {
        return "";
    }

    @Override
    public String visitFunction_call_expression_with_closure(Swift3Parser.Function_call_expression_with_closureContext ctx){
        return "";
    }
}
