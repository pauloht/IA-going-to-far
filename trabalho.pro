%exercicios
%Concatenar Listas
%conc(Lentrada1,Lentrada2,Lsaida)
conc([],Ly,Ly):-!.
conc([H|T1],L,[H|T2]) :- conc(T1,L,T2).

%Somar elementos de uma lista de numeros
%soma(Lentrada,Resultado)
soma([],0):-!.
soma([H|T],X):-soma(T,H2),X is H+H2.

%Tamanho da lista
%len(Lentrada,Resultado).
len([],0):-!.
len([H|T],X) :- len(T,X2),X is X2+1.

%Media aritmetica de uma lista de numeros
%media(Lent,resul)
media([],0):-!.
media(Lentrada,Resultado) :- soma(Lentrada,Soma),len(Lentrada,Len),Resultado is Soma/Len.

%Fatorial de um valor inteiro
%fat(InterIn,Resul)
fat(X,Y) :- X < 0,Y is 0,!.
fat(0,1) :- !.
fat(1,1) :- !.
fat(X,Y) :- X2 is X-1,fat(X2,Y2),Y is X*Y2.

%Inserir elemento a esquerda da lista
%ie(Elemento,ListaEntrada,ListaSaida)
ie(X,[],[X]):-!.
ie(X,[H|T],[X,H|T]):-!.

%Outro jeito de fazer
%ie_alt(Elemento,ListaEntrada,ListaSaida)
ie_alt(X,[],[X]):-!.
ie_alt(X,L,[X|L]):-!.

%Inserir a direita
%id(Elemento,Lent,Lsaida)
id(X,[],[X]) :- !.
id(X,[H|T],[H|T2]):-id(X,T,T2).

%remover ultimo elemento de uma lista
%rem_last(Lentrada,Lsaida)
rem_last([],[]).
rem_last([H|[]],[]):-!.
rem_last([H|T],[H|T2]):-rem_last(T,T2).

%remover primeiro elemento de uma lista
%rem_first(Lentrada,Lsaida)
rem_first([],[]):-!.
rem_first([H|T],T):-!.

%remover elemento de uma lista
%rem(Elemento,ListaEntrada,ListaSaida)
rem(X,[],[]):-!.
rem(X,[X|T],T):-!.
rem(X,[A|T],[A|T2]):-rem(X,T,T2).

%Pegar ultimo elemento da ultima lista
%get_last(Lista,UltimoElemento)
get_last([],[]):-!.
get_last([H|[]],H):-!.
get_last([H|T],X):-get_last(T,X).

%Remove todos os elementos x de uma lista
%rem_all_x(Elemento,Lentrada,Lsaida)
rem_all_x(X,[],[]):-!.
rem_all_x(X,[X|T],T2):-rem_all_x(X,T,T2),!.
rem_all_x(X,[Y|T],[Y|T2]):-rem_all_x(X,T,T2).

%retorna true se elemento esta na lista, false caso contrario
%is_x_in_list(Elemento,Lista)
is_x_in_list(X,[]):-2<1.%essa linha nao prescissa
is_x_in_list(X,[X|T]):-!.
is_x_in_list(X,[H|T]):-is_x_in_list(X,T).

%Elementos repetidos entre listas
%l_repeat(Lentrada1,Lentrada2,Lsaida)
list_repeat([H|T],L2,[H|T2]) :- is_x_in_list(H,L2),list_repeat(T,L2,T2),!.
list_repeat([H|T],L2,T2) :- list_repeat(T,L2,T2),!.
list_repeat([],L2,[]) :- !.

%rem_all_repetidos(Lentrada,Lsaida) chamada
%rem_all_repetidos(Lentrada,Lsaida,Lrepetidos) em codigo
%query exemplo : rem_all_repetidos([1,1,1,2,2,1,1,3,3,3,2,3,4,5,4,2,3,5,7,9,0,8,7,7,8,9],X).
rem_all_repetidos(X,Y):-rem_all_repetidos(X,Y,[]),!.
rem_all_repetidos([],[],_):-!.
rem_all_repetidos([H|T],Lsaida,Lrepetidos) :- is_x_in_list(H,Lrepetidos),rem_all_repetidos(T,Lsaida,Lrepetidos),!.
rem_all_repetidos([H|T],[H|T2],Lrepetidos) :- id(H,Lrepetidos,Lsaida),rem_all_repetidos(T,T2,Lsaida).

%substitue a primeira ocorrencia de x por um y
%swap_first_x_for_y(X,Y,ListaEntrada,ListaSaida)
swap_first_x_for_y(X,Y,[],[]):-!.
swap_first_x_for_y(X,Y,[X|T],[Y|T]):-!.
swap_first_x_for_y(X,Y,[H|T],[H|T2]):-swap_first_x_for_y(X,Y,T,T2).
%query exemplo : swap_first_x_for_y(a,5,[d,2,a,x,4,x,5,y,5],X).

%Pega maior valor de uma lista
%get_maior(Lista,Resposta)
get_maior([],0):-!.
get_maior([H|T],X) :- get_maior(T,X,H).
get_maior([],X,M) :- X is M,!.
get_maior([H|T],X,M) :- H>M,get_maior(T,X,H),!.
get_maior([H|T],X,M) :- get_maior(T,X,M).

%Pega menor valor de uma lista
%get_menor(Lista,Resposta)
get_menor([],0):-!.
get_menor([H|T],X) :- get_menor(T,X,H).
get_menor([],X,M) :- X is M,!.
get_menor([H|T],X,M) :- H<M,get_menor(T,X,H),!.
get_menor([H|T],X,M) :- get_menor(T,X,M).

%inverter uma lista usando insercao a esquerda
%inverter(Lentrada,Lsaida)
inverter([],[]) :- !.
inverter(X,Y) :- inverter(X,Y,[]),!.
inverter([],Y,Z) :- Z = Y,!.
inverter([H|T],Y,Z) :- ie(H,Z,Z2),inverter(T,Y,Z2).
%query exemplo : inverter([1,2,3,4,5,g,a,sf,2,ad,a],X).

%pegar elemento na i-nesima posicao
%get_x_at_pos_y(Posicao,Lentrada,ElementoSaida)
get_x_at_pos_y(_,[],[]) :- !.
get_x_at_pos_y(X,[H|T],Y) :- X =< 1,Y=H,!.
get_x_at_pos_y(X,[H|T],Y) :- X2 is X-1,get_x_at_pos_y(X2,T,Y).
%query exemplo : get_x_at_pos_y(8,[1,2,3,4,5,g,a,sf,2,ad,a],X).

%substituir todas as ocorrencias de x por y 
%sub_all_x_for_y(Elementox,Elementoy,Lentrada,Lsaida)
sub_all_x_for_y(X,Y,[X|[]],[Y|[]]) :- !.
sub_all_x_for_y(X,Y,[W|[]],[W|[]]) :- !.
sub_all_x_for_y(X,Y,[X|T],[Y|T2]) :- sub_all_x_for_y(X,Y,T,T2),!.
sub_all_x_for_y(X,Y,[W|T],[W|T2]) :- sub_all_x_for_y(X,Y,T,T2),!.

%gerar todas as sub_listas
%sub_list(ListaEntrada,ListaSaida)
%exemplo query sub_list([1,2,3,4,5,6,7,8,9],X).
sub_list([H|[ ]],[H|[ ]]).%se eh ultimo elemento
sub_list([H|[ ]],[ ]):-!.%se eh ultimo elemento
sub_list([H|T],[H|T2]) :- sub_list(T,T2).%se nao eh ultimo elemento
sub_list([H|T],T2) :- sub_list(T,T2).%se nao eh ultimo elemento

%uniao de duas listas, ou seja nao tem elemento repetidos
%uniao(Lentrada1,Lentrada2,Lsaida)
%uniao(L1,L2,L3) :- uniao(L1,L2,L3),!.
uniao(L1,L2,Lsaida) :- conc(L1,L2,Lconc),rem_all_repetidos(Lconc,Ls),Ls = Lsaida.

%uniao_2(Lentrada,Lentrada2,Lsaida)
%uniao de duas listas sem usar rem_all_repetidos,criando copia de L usando conc
uniao_2(L,L2,L3) :- conc([],L,L4),uniao_2(L,L2,L3,L4),!.
uniao_2([H|T],L2,[H|T2],L4) :- uniao_2(T,L2,T2,L4),!.
uniao_2([],[H|T],T2,L4) :- is_x_in_list(H,L4),uniao_2([],T,T2,L4),!.
uniao_2([],[H|T],[H|T2],L4) :- uniao_2([],T,T2,L4),!.
uniao_2([],[],[],L4) :- !.

%uniao_2(Lentrada,Lentrada2,Lsaida)
%uniao de duas listas sem usar rem_all_repetidos, criando copia de L usando atribuiчуo e usando member em vez de is_x_in_list
uniao_3(L,L2,L3) :- L4 = L,uniao_3(L,L2,L3,L4),!.
uniao_3([H|T],L2,[H|T2],L4) :- uniao_3(T,L2,T2,L4),!.
uniao_3([],[H|T],T2,L4) :- member(H,L4),uniao_3([],T,T2,L4),!.
uniao_3([],[H|T],[H|T2],L4) :- uniao_3([],T,T2,L4),!.
uniao_3([],[],[],L4) :- !.

%intersecao entre duas listas
%intersecao(Lentrada1,Lentrada2,Lsaida)
%exemplo query intersecao([1,2],[3,4,5,6,7,8,9],X).
%exemplo query intersecao([1,2,3,4,5,6],[3,4,5,6,7,8,9],X).
intersecao(L1,L2,L3) :- list_repeat(L1,L2,Lsaida),L3 = Lsaida.

%Pegar valor absoluto
%valorAbsoluto(entrada,saida,sinalsaida)
%saida щ igual valor absoluto da entrada
%sinal saida eh 0 se a entrada eh positiva e 1 se a entrada eh negativa
valorAbsoluto(E,S,P) :- E >= 0,S is E,P is 0,!.
valorAbsoluto(E,S,N) :- E < 0,S is E * -1,N is 1,!.

%Transforma uma entrada inteira em uma lista binaria da sua representacao sem sinal
%binarizar(E,S)
%E eh uma entrada inteira, S uma lista binaria correspondente a representacao de E binaria sem sinal
%caso 1 a entrada щ maior que 1 e щ par
binarizar(E,[0|T]) :- E>1,0 is mod(E,2),A is E//2,binarizar(A,T),!.
%caso 2 a entrada щ maior que 1 e щ impar
binarizar(E,[H|T]) :- E>1,1 is mod(E,2),E2 is (E-1)//2,H is 1,binarizar(E2,T),!.
%caso 3 a entrada щ 1
binarizar(1,[1|[]]) :- !.
%caso 4 a entrada щ 0
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
%Se a lista tiver tamanho impar o ultimo elemento da lista щ ignorado e a lista e divida pelas outras partes igualmente
dividirLista(L,L1,L2) :- len(L,Tamanho),MetadeTamanho is Tamanho//2,dividirLista(L,L1,L2,MetadeTamanho,0).
dividirLista([H|T],[H|T2],L2,Metade,Contador) :- Contador < Metade,Contador2 is Contador+1,dividirLista(T,T2,L2,Metade,Contador2),!.
dividirLista([H|T],L2,[H|T2],Metade,Contador) :- Contador < 2*Metade,Contador2 is Contador+1,dividirLista(T,L2,T2,Metade,Contador2),!.
dividirLista(Fim,[],[],X,Y) :- !.

%Predicado que diz se uma lista щ palindrome, se for S щ 1 se nao S щ 0.
%palindrome(Lista,S)
%Primeiro caso onde Lista щ par
palindrome(Lista) :- len(Lista,TamanhoLista),0 is mod(TamanhoLista,2),dividirLista(Lista,Lesquerda,Ldireita),inverter(Ldireita,Ldireitainvertida),compararDuasListas(Lesquerda,Ldireitainvertida),!.
palindrome(Lista) :- len(Lista,TamanhoLista),1 is mod(TamanhoLista,2),MetadeTamanho is (TamanhoLista + 1)//2,rem_x_at(Lista,MetadeTamanho,ListaSaida),palindrome(ListaSaida),!.

%PREDICADO PARA QUESTAO 1
%notacao :
%questao1(E,S).
%onde E щ um inteiro e S щ o vetor binario representado em sinal magnitude de E.
%EX : questao1(5,S).
questao1(E,S) :-  valorAbsoluto(E,EAbsoluto,Sinal),binarizar(EAbsoluto,Lista),inverter(Lista,ListaArrumada),ie(Sinal,ListaArrumada,RespostaFinal),S = RespostaFinal,!.

%PREDICADO PARA QUESTAO 2
%questao2(Lista,Saida)
%onde Lista щ uma lista de 0 e 1 representando um numero binario em sinal magnitude e Saida щ um inteiro em aberto equivalente ao valor dado pela lista binaria
%EX : questao2([1,1,1,0,0,1,1],S).
questao2([0|T],S) :- (desbinarizar(T,Resultado),S is Resultado),!.
questao2([1|T],S) :- desbinarizar(T,Resultado),S is (Resultado * -1),!.

%PREDICADO PARA QUESTAO3
%questao3(Conjunto1,Conjunto2)
%Dadas dois conjuntos (em forma de listas), retorna se sao disjuntos ou nao 
%EX : questao3([1,2],[3,4). = True/Yes
questao3(H,S) :- uniao(H,S,Resultante), len(H,LenH), len(S, LenS),
    			 len(Resultante, LenR), LenR is LenH+LenS.

%PREDICADO PARA QUESTAO 6
%questao6(Lista)
%Onde lista щ uma Lista formando a palavra que deseja se verificar se щ palindrome, retorna false se nao for,true caso contrario
%EX : questao6([a,n,a]).
%EX : questao6([a,m,a,n,a,p,l,a,n,a,g,o,d,s,n,a,m,t,a,b,l,e,s,n,i,t,r,a,t,e,t,a,r,t,i,n,s,e,l,b,a,t,m,a,n,s,d,o,g,a,n,a,l,p,a,n,a,m,a]).
questao6(Lista) :- palindrome(Lista),!.


%fim exercicios
