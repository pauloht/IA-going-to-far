%Funcao member. Retorna true se X e membro de uma lista
%Uso: member(X, Lista).
member(X, [Y|T]) :- X = Y; member(X, T).
%Implementacao da funcao member, ja que estava
%tendo problemas com a padrao

%********************SIMULACAO DE AUTOMATOS DE PILHA******************************
%Baseado no exemplo de automatos finitos do professor Josue

%Inserir elemento a esquerda da lista
%ie(Elemento,ListaEntrada,ListaSaida)
ie(X,[],[X]):-!.
ie(X,[H|T],[X,H|T]):-!.

%Faz Pop primeiro, depois push
%Pop e o primeiro elemento devem ser iguais
%Retorna em NovaPilha
pushpop([], Push, [], [Push]):-!.
pushpop(Pop, Push, H, H) :- Pop = '#', Push = '#', !. %Case: Pop e Push vazio
pushpop(Pop, Push, H, NovaPilha) :- Pop = '#', ie(Push,H,NovaPilha),!. %Case: Pop vazio
pushpop(Pop, Push, [H|T], T) :- Push = '#', Pop = H,!. %Case: Push vazio
pushpop(Pop, Push, [H|T], NovaPilha) :- Pop = H, ie(Push,T,NovaPilha),!.

%Reconhecimento de cadeias
re([], Qa, Qa, []) :- !. %Aceitacao: String vazia, estado atual eh final, pilha vazia

re([Head|Tail], Qa, Qr, P) :- 
	d(Qa, Head, Pop, Q1, Push),
	%write('Pop: '), write(Pop), nl,
	%write('Push: '), write(Push), nl,
	pushpop(Pop, Push, P, NovaPilha),
	%write('Estado Qa: '), write(Qa), nl,
	%write('Estado q1: '), write(Q1), nl,
	%write('NPilha: '), write(NovaPilha), nl,
	re(Tail, Q1, Qr, NovaPilha).

re(H, Qa, Qr, P) :- %Caso onde ha uma transicao em vazio
	d(Qa, '#', Pop, Q1, Push),
	%write('Pop: '), write(Pop), nl,
	%write('Push: '), write(Push), nl,
	pushpop(Pop, Push, P, NovaPilha),
	%write('Estado Qa: '), write(Qa), nl,
	%write('Estado q1: '), write(Q1), nl,
	%write('NPilha: '), write(NovaPilha), nl,
	re(H, Q1, Qr, NovaPilha).
	
reconhece(X, Qa, F, Qr, P) :- re(X, Qa, Qr, P),
							  %write('ACPilha: '), write(P), nl,
							  %write('Qa: '), write(Qa), nl,
							  %write('Qf: '), write(Qr), nl,
							  %write('EstadosFinais: '), write(F), nl,
							  member(Qr, F), nl, write('ACEITA!'), nl, !.
%nl--> nova linha no prolog.
reconhece(X, Qa, F, Qr, P) :- nl, write('RECUSADA!'), nl, !.

%Definicao do automato
define(Q, A, R, Q0, F) :- nl, write('Definicao do automato'), nl, 
			write('Entre com a lista de estados:'), 
			read(Q), nl,
			write('Entre com o alfabeto da fita:'),
			read(A), nl,
			write('Entre com o alfabeto da pilha:'), 
			read(R), nl,
			write('Entre com o estado inicial:'),
			read(Q0), nl,
			write('Entre com a lista de estados finais:'),
			read(F), nl.

%Definicao das transicoes.
defined :- nl,                                %estado atual, simb, Pop, prox. estado, Push
	   write('Entre com as transicoes no formato d(q0, a, P0, q1, P1).'), nl,
	   write('O caracter # eh o vazio.'), write('quit encerra:'), nl,
	   repeat,
		read(B),
		assert(B),
	   B == quit. 
	 
%PROGRAMA PRINCIPAL
a :- write('Implementacao de AP!'), nl,
    %retractall(d(_,_,_)),
    define(Q, A, R, Q0, F),
    defined, 
    repeat,
	write('Entre com a cadeia (quit encerra):'),
	read(X),
	reconhece(X, Q0, F, Qr, P), nl, 
	%X:string Q0:ini F:listaFinais Qr:UmDosFinais P:pilha
     X == quit,
     write('FIM DE PROGRAMA!!!'),nl.
	 
 %Exemplo: linguagem: a^ib^i |i>=0
 % M=({1,2},{a,b},X,Sigma,1,{1,2})
 % Sigma(1,a,Lamba)=[1,x]      ---Lamba = vazio
 % Sigma(1,b,Lamba)=[2,Lamba]
 % Sigma(2,b,x)=[2,Lamba]
 
 %TESTE -> em ordem de insercao  --- # eh lambda
 %[1,2].
 %['a','b'].
 %'X'.
 %1.
 %[1,2].
 %d(1,'a','#',1,'X').
 %d(1,'b','X',2,'#').
 %d(2,'b','X',2,'#').
 %quit.
 %['a','a','b','b'].
 %quit.
 
 %Deverao ser as computacoes: {Estado, string, pilha}
 %[1,aabb,Lamba]
 %[1,abb,X]
 %[1,bb,XX]
 %[2,b,X]
 %[2,Lamba,Lamba]