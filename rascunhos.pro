%Casos de teste.
%([1,2,3],[1,2,3])
%([1,6,36],[2,12,72])
%([2,12,72], B)
%([2,12,72],[4,24,144]).

%Tamanho da lista
%len(Lentrada,Resultado).
len([],0):-!.
len([H|T],X) :- len(T,X2),X is X2+1.

%Retorna a lista B, dado a lista A
%e a definicao da funcao do exercicio
calcB([],Razao,[]) :- !.
calcB([H|T],Razao,[R0|R]) :- getNext(H,Razao,N),
    				calcBTerm(H,N,R0),
    				calcB(T,Razao,R).
calcBTerm(A,A1,R) :- R is (((2*A)+A1)/4).

%Calcula a razao entre dois termos
getRazao(A0,A1,R) :- R is A1/A0.

%Dado um numero e uma razao de uma
%progressao, calcula o proximo termo
getNext(H,Razao,H1) :- H1 is H*Razao.

%Pela definicao da funcao do exercicio,
%calcula um termo, dados "A[i]", "A[i+1]",
%e "B[I]". O termo devera ser zero.
calcTerm(A0,A1,B,R) :- R is ((2*A0)+A1-(4*B)).

%Resolve a questao5, dadas duas listas nao
%vazias
solve([],[], Razao) :- !. %Ambas vazias, true
solve(H,T, Razao) :- len(H,LenH), 
    				len(T,LenT),
   					LenH  =\= LenT, 
   					!, false. %Tamanhos diferentes, false.
					
solve([H|T], [S|R], Razao) :- getNext(H,Razao,H1),
    						calcTerm(H,H1,S,Term),
    						Term is 0,
    						solve(T,R,Razao).


%PREDICADO QUESTAO 5 - DUAS LISTAS NAO VAZIAS							
questao5([H,T|X],[S|R]) :- getRazao(H,T,Razao), solve([H,T|X],[S|R],Razao).

%PREDICADO QUESTAO 5 - LISTA R EM ABERTO
questao52([H,T|X],R) :- getRazao(H,T,Razao), calcB([H,T|X], 6, R),!.
