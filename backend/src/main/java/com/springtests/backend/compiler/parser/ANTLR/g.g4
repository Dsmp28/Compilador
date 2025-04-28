grammar g;
prog
  : stat+ EOF
  ;

stat
  : IDENT ASSIGN expr SEMI?    # AssignStat
  ;

expr
  : expr '^' expr              # Power
  | expr '*' expr              # Mul
  | expr '/' expr              # Div
  | expr '+' expr              # Add
  | expr '-' expr              # Sub
  | atom                       # AtomExpr
  ;

atom
  : NUMBER                     # NumberAtom
  | IDENT                      # IdentAtom
  | '[' expr ']'               # BracketExpr
  ;

ASSIGN : '<-' ;
SEMI   : ';' ;
NUMBER : [0-9]+ ;
IDENT  : [a-zA-Z_] [a-zA-Z0-9_]* ;
WS     : [ \t\r\n]+ -> skip ;