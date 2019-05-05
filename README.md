program -> block

block -> {decls stmts}

decls -> decls decl | E

decl -> type id

type -> type[num] | basic

stmts -> stmts stmt | E

stmt -> loc = bool | if(bool) stmt | while(bool) stmt | do stmt while(bool) | block

loc -> loc[bool] | id

bool -> bool || join | join

join -> join && equality | equality

equality -> equality == rel | equality != rel | rel

rel -> expr < expr | expr <= expr | expr > expr | expr >= expr | expr

expr -> expr + term | expr - term | term

term -> term * unary | term / unary | unary

unary -> !unary | -unary | factor

factor -> (bool) | num | loc | rel | true | false
