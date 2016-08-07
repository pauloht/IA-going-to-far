%Casos de teste.
%([1,2,3],[1,2,3]).
%([1,6,36],[2,12,72]).
%([2,12,72], B).
%([2,12,72],[4,24,144]).

%Tamanho da lista
%len(Lentrada,Resultado).
len([],0):-!.
len([H|T],X) :- len(T,X2),X is X2+1.

%Retorna a lista B, dado a lista A
%e a definicao da funcao do exercicio
calcB([],[]) :- !.
calcB([H|T],[R0|R]) :- R0 is H*2,
    				calcB(T,R).

%Resolve a questao5, dadas duas listas nao
%vazias
validate([],[]) :- !. %Ambas vazias, true
validate(H,T) :-len(H,LenH), 
    			len(T,LenT),
   				LenH  =\= LenT, 
   				!, false. %Tamanhos diferentes, false.

validate([H],[T]) :- T is H*2, !.
validate([H,T|X], [S,R|Y]) :- T is H*6,
    						S is H*2,
    						R is S*6,
    						validate(X,Y).

%PREDICADO QUESTAO 5 - DUAS LISTAS NAO VAZIAS							
questao5(H,T) :- validate(H,T).

%PREDICADO QUESTAO 5 - LISTA R EM ABERTO
questao52([H,T|X],R) :- calcB([H,T|X], R).