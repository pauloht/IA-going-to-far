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
pushpop([], Push, T, NovaPilha) :- ie(Push,T,NovaPilha),!.

%Case: Pop vazio
pushpop(Pop, Push, H, NovaPilha) :- Pop = '#',
									ie(Push,H,NovaPilha),!.
%Case: Push vazio
pushpop(Pop, Push, [H|T], T) :- Push = '#', !.

%Case: PopPush vazio
pushpop(Pop, Push, H, H) :- Pop = '#',
							Push = '#', !.

pushpop(Pop, Push, [Pop|T], NovaPilha) :- ie(Push,T,NovaPilha),!.

%Reconhecimento de cadeias
re([], Qa, Qa, []) :- !. %Aceitacao: String vazia, estado atual eh final, pilha vazia
re([Head|Tail], Qa, Qr, P) :- 
	d(Qa, Head, Pop, Q1, Push),
	write(Pop), nl,
	write(Push), nl,
	pushpop(Pop, Push, P, NovaPilha),
	write(Head), nl,
	write(Tail), nl,
	write(Qa), nl,
	write(Qr), nl,
	write(P), nl,
	re(Tail, Q1, Qr, NovaPilha).

reconhece(X, Qa, F, Qr, P) :- re(X, Qa, Qr, P) , member(Qr, F), nl, write('ACEITA!'), nl, !.
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
defined :- nl,
	   write('Entre com as transicoes no formato d(q0, a, P0, q1, P1).'), 
	   %estado atual, simb, Pop, prox. estado, Push
	   write('quit encerra:'), nl,
	   repeat,
		read(B),
		assert(B),
	   B == quit.

%PROGRAMA PRINCIPAL
a :- nl, nl, nl,
     write('Implementacao de AP!'), nl,
     %retractall(d(_,_,_)),
     define(Q, A, R, Q0, F),
     defined, 
     repeat,
	write('Entre com a cadeia (quit encerra):'),
	read(X),
	reconhece(X, Q0, F, Qr, P), nl, %X:string Q0:ini F:listaFinais Qr:UmDosFinais P:pilha
     X == quit,
     write('FIM DE PROGRAMA!!!').
	 
 %Exemplo: linguagem: a^ib^i |i>=0
 % M=({1,2},{a,b},X,Sigma,1,{1,2})
 % Sigma(1,a,Lamba)=[1,x]      ---Lamba = vazio
 % Sigma(1,b,Lamba)=[2,Lamba]
 % Sigma(2,b,x)=[2,Lamba]
 
 %TESTE -> em ordem de insercao
 %[1,2].
 %[a,b].
 %X.
 %1.
 %[1,2].
 %d(1,a,'#',1,X).     # eh Lamba
 %d(1,b,'#',2,'#').
 %d(2,b,X,2,'#').
 %quit.
 %[a,a,b,b].
 %quit.
 
 %Deverao ser as computacoes: {Estado, string, pilha}
 %[1,aabb,Lamba]
 %[1,abb,X]
 %[1,bb,XX]
 %[2,b,X]
 %[2,Lamba,Lamba]