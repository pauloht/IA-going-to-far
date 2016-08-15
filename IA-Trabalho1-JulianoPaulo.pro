%exercicios
%Concatenar Listas
%conc(Lentrada1,Lentrada2,Lsaida)
conc([],Ly,Ly):-!.
conc([H|T1],L,[H|T2]) :- conc(T1,L,T2).

%Tamanho da lista
%len(Lentrada,Resultado).
len([],0):-!.
len([H|T],X) :- len(T,X2),X is X2+1.

%Inserir elemento a esquerda da lista
%ie(Elemento,ListaEntrada,ListaSaida)
ie(X,[],[X]):-!.
ie(X,[H|T],[X,H|T]):-!.

%Inserir a direita
%id(Elemento,Lent,Lsaida)
id(X,[],[X]) :- !.
id(X,[H|T],[H|T2]):-id(X,T,T2).

%remover elemento de uma lista
%rem(Elemento,ListaEntrada,ListaSaida)
rem(X,[],[]):-!.
rem(X,[X|T],T):-!.
rem(X,[A|T],[A|T2]):-rem(X,T,T2).

%retorna true se elemento esta na lista, false caso contrario
%is_x_in_list(Elemento,Lista)
is_x_in_list(X,[]):-2<1.%essa linha nao prescissa
is_x_in_list(X,[X|T]):-!.
is_x_in_list(X,[H|T]):-is_x_in_list(X,T).

%rem_all_repetidos(Lentrada,Lsaida) chamada
%rem_all_repetidos(Lentrada,Lsaida,Lrepetidos) em codigo
%query exemplo : rem_all_repetidos([1,1,1,2,2,1,1,3,3,3,2,3,4,5,4,2,3,5,7,9,0,8,7,7,8,9],X).
rem_all_repetidos(X,Y):-rem_all_repetidos(X,Y,[]),!.
rem_all_repetidos([],[],_):-!.
rem_all_repetidos([H|T],Lsaida,Lrepetidos) :- is_x_in_list(H,Lrepetidos),rem_all_repetidos(T,Lsaida,Lrepetidos),!.
rem_all_repetidos([H|T],[H|T2],Lrepetidos) :- id(H,Lrepetidos,Lsaida),rem_all_repetidos(T,T2,Lsaida).

%inverter uma lista usando insercao a esquerda
%inverter(Lentrada,Lsaida)
inverter([],[]) :- !.
inverter(X,Y) :- inverter(X,Y,[]),!.
inverter([],Y,Z) :- Z = Y,!.
inverter([H|T],Y,Z) :- ie(H,Z,Z2),inverter(T,Y,Z2).
%query exemplo : inverter([1,2,3,4,5,g,a,sf,2,ad,a],X).

%uniao de duas listas, ou seja nao tem elemento repetidos
%uniao(Lentrada1,Lentrada2,Lsaida)
%uniao(L1,L2,L3) :- uniao(L1,L2,L3),!.
uniao(L1,L2,Lsaida) :- conc(L1,L2,Lconc),rem_all_repetidos(Lconc,Ls),Ls = Lsaida.

%Pegar valor absoluto
%valorAbsoluto(entrada,saida,sinalsaida)
%saida é igual valor absoluto da entrada
%sinal saida eh 0 se a entrada eh positiva e 1 se a entrada eh negativa
valorAbsoluto(E,S,P) :- E >= 0,S is E,P is 0,!.
valorAbsoluto(E,S,N) :- E < 0,S is E * -1,N is 1,!.

%Transforma uma entrada inteira em uma lista binaria da sua representacao sem sinal
%binarizar(E,S)
%E eh uma entrada inteira, S uma lista binaria correspondente a representacao de E binaria sem sinal
%caso 1 a entrada é maior que 1 e é par
binarizar(E,[0|T]) :- E>1,0 is mod(E,2),A is E//2,binarizar(A,T),!.
%caso 2 a entrada é maior que 1 e é impar
binarizar(E,[H|T]) :- E>1,1 is mod(E,2),E2 is (E-1)//2,H is 1,binarizar(E2,T),!.
%caso 3 a entrada é 1
binarizar(1,[1|[]]) :- !.
%caso 4 a entrada é 0
binarizar(0,[0|[]]) :- !.

%Fazer potencia de um valor
%pow(A,B,S), potencia de A elevado a B com o resultado em S
pow(A,0,1) :- !.
pow(A,1,A) :- !.
pow(A,B,S) :- B2 is B-1,pow(A,B2,S2),S is S2*A,!.
pow(A,X,0) :- !.

%Predicado que remove X na i-esima posicao da lista
%rem_x_at(ListaEntrada,Posicao,ListarSaida)
rem_x_at(L,P,S) :- rem_x_at(L,P,S,1),!.
rem_x_at([H|T],P,T2,P) :- C2 is P+1,rem_x_at(T,P,T2,C2),!. 
rem_x_at([H|T],P,[H|T2],C) :- C2 is C+1,rem_x_at(T,P,T2,C2),!.
rem_x_at([],_,[],_) :- !. 

%Transforma uma sequencia de lista de 0 1(representacao binaria) em um inteiro positivo)
desbinarizar(Lista,Resultado) :- inverter(Lista,ListaInvertida),desbinarizar(ListaInvertida,0,ResultadoFinal),Resultado is ResultadoFinal.
desbinarizar([1|[]],Contador,Resultado) :- pow(2,Contador,Saida),Resultado is Saida,!.
desbinarizar([0|[]],Contador,0) :- !.
desbinarizar([1|T],Contador,Resultado) :- pow(2,Contador,Saida),Contador2 is Contador+1,desbinarizar(T,Contador2,Resultado2),Resultado is Saida+Resultado2,!.
desbinarizar([0|T],Contador,Resultado) :- Contador2 is Contador+1,desbinarizar(T,Contador2,Resultado2),Resultado is Resultado2,!.

%Predicado que compara duas listas e retorna true se as listas forem iguais false caso contrario
%compararDuasListas(Lista1,Lista2).
compararDuasListas([],[]) :- !.
compararDuasListas([H|T1],[H|T2]) :- compararDuasListas(T1,T2),!.

%Predicado que pega uma Lista L e divide na metade em duas sub-listas
%dividirLista(L,L1,L2), L lista entrada L1 lista a esquerda e L2 lista a direita
%Se a lista tiver tamanho impar o ultimo elemento da lista é ignorado e a lista e divida pelas outras partes igualmente
dividirLista(L,L1,L2) :- len(L,Tamanho),MetadeTamanho is Tamanho//2,dividirLista(L,L1,L2,MetadeTamanho,0).
dividirLista([H|T],[H|T2],L2,Metade,Contador) :- Contador < Metade,Contador2 is Contador+1,dividirLista(T,T2,L2,Metade,Contador2),!.
dividirLista([H|T],L2,[H|T2],Metade,Contador) :- Contador < 2*Metade,Contador2 is Contador+1,dividirLista(T,L2,T2,Metade,Contador2),!.
dividirLista(Fim,[],[],X,Y) :- !.

%Predicado que diz se uma lista é palindrome, se for S é 1 se nao S é 0.
%palindrome(Lista,S)
%Primeiro caso onde Lista é par
palindrome(Lista) :- len(Lista,TamanhoLista),0 is mod(TamanhoLista,2),dividirLista(Lista,Lesquerda,Ldireita),inverter(Ldireita,Ldireitainvertida),compararDuasListas(Lesquerda,Ldireitainvertida),!.
palindrome(Lista) :- len(Lista,TamanhoLista),1 is mod(TamanhoLista,2),MetadeTamanho is (TamanhoLista + 1)//2,rem_x_at(Lista,MetadeTamanho,ListaSaida),palindrome(ListaSaida),!.

%Predicado que faz o Bubblesort da lista passada
%bubblesort(ListaEntrada,ListaSaida)
bubblesort(ListaEntrada,ListaSaida) :- len(ListaEntrada,Tamanho),bubblesort(ListaEntrada,ListaSaida,Tamanho),!.
bubblesort(ListaEntrada,ListaSaida,X) :- X =< 0,ListaSaida = ListaEntrada,!.
bubblesort(ListaEntrada,ListaSaida,X) :- bubblesortIteracao(ListaEntrada,ListaIterada),X2 is X - 1,bubblesort(ListaIterada,ListaSaida,X2),!.

%fazer uma iteracao
bubblesortIteracao([H|[]],[H|[]]) :- !.
bubblesortIteracao([H,H2|T],[H|T2]) :- H =< H2,bubblesortIteracao([H2|T],T2),!. 
bubblesortIteracao([H,H2|T],[H2|T2]) :- H > H2,bubblesortIteracao([H|T],T2),!.
bubblesortIteracao(_,[]) :- write('******TRAP ATIVADA********'),nl,!.

%Retorna a lista B, dado a lista A
%e a definicao da funcao do exercicio
%USADA PARA O EXERCICIO 5
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

%PREDICADO PARA QUESTAO 1
%notacao :
%questao1(E,S).
%onde E é um inteiro e S é o vetor binario representado em sinal magnitude de E.
%EX : questao1(5,S).
questao1(E,S) :-  valorAbsoluto(E,EAbsoluto,Sinal),binarizar(EAbsoluto,Lista),inverter(Lista,ListaArrumada),ie(Sinal,ListaArrumada,RespostaFinal),S = RespostaFinal,!.

%PREDICADO PARA QUESTAO 2
%questao2(Lista,Saida)
%onde Lista é uma lista de 0 e 1 representando um numero binario em sinal magnitude e Saida é um inteiro em aberto equivalente ao valor dado pela lista binaria
%EX : questao2([1,1,1,0,0,1,1],S).
questao2([0|T],S) :- (desbinarizar(T,Resultado),S is Resultado),!.
questao2([1|T],S) :- desbinarizar(T,Resultado),S is (Resultado * -1),!.

%PREDICADO PARA QUESTAO3
%questao3(Conjunto1,Conjunto2)
%Dadas dois conjuntos (em forma de listas), retorna se sao disjuntos ou nao 
%EX : questao3([1,2],[3,4). = True/Yes
questao3(H,S) :- uniao(H,S,Resultante), len(H,LenH), len(S, LenS),
    			 len(Resultante, LenR), LenR is LenH+LenS.

%PREDICADO PARA QUESTAO4
%questao4(Conjunto1,Conjunto2)
%Dadas dois conjuntos (em forma de listas), retorna se sao iguais ou nao 
%EX : questao([1,2,3],[3,2,1). = True/Yes
questao4(H,S) :- len(H, LenH), len(S, LenS), LenH =\= LenS,!, 1 == 0. %Tamanhos diferentes, forca falso.
questao4([],[]) :- !.
questao4([H|T],S) :- rem(H,S,Ret), questao4(T,Ret).

%PREDICADO QUESTAO 5 - DUAS LISTAS NAO VAZIAS
%Ex.: questao5([2,12,72],[4,24,144]). - Yes/True
%Ex.: questao5([1,2,3],[1,2,3]). - No/False						
questao5(H,T) :- validate(H,T).
%PREDICADO QUESTAO 5 - LISTA R EM ABERTO
%Ex.: questao5([2,12,72],B). - B=[4,24,144].
questao5([H,T|X],R) :- calcB([H,T|X], R).

%PREDICADO PARA QUESTAO 6
%questao6(Lista)
%Onde lista é uma Lista formando a palavra que deseja se verificar se é palindrome, retorna false se nao for,true caso contrario
%EX : questao6([a,n,a]).
%EX : questao6([a,m,a,n,a,p,l,a,n,a,g,o,d,s,n,a,m,t,a,b,l,e,s,n,i,t,r,a,t,e,t,a,r,t,i,n,s,e,l,b,a,t,m,a,n,s,d,o,g,a,n,a,l,p,a,n,a,m,a]).
questao6(Lista) :- palindrome(Lista),!.

%PREDICADO PARA QUESTAO 7
%questao7(ListaEntrada,ListaSaida)
%Onde ListaEntrada é a lista que deseja ordenar por bubblesort e a ListaSaida e a variavel em aberto que recebera a resposta
%EX : questao7([-1,-5,-99,5,9,1,0,-3,5])
questao7(ListaEntrada,ListaSaida) :- bubblesort(ListaEntrada,ListaSaida),!.

%fim exercicios
