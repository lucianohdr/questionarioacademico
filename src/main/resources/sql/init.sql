/*Inserindo perfis no sistema*/
INSERT INTO perfil (id,descricao,nome) values (1, 'Administrador do Sistema', 'Administrador');
INSERT INTO perfil (id,descricao,nome) values (2, 'Coordenador do Curso', 'Coordenador');
INSERT INTO perfil (id,descricao,nome) values (3, 'Chefe de Departamento', 'Chefe');
INSERT INTO perfil (id,descricao,nome) values (4, 'Professor', 'Professor');
INSERT INTO perfil (id,descricao,nome) values (5, 'Aluno', 'Aluno');
/*Inserindo perfis no sistema*/
INSERT INTO categoriaquestionario (id,descricao,nome ) values (1, 'Auto-avaliação do aluno', 'Auto-avaliação Aluno');
INSERT INTO categoriaquestionario (id,descricao,nome ) values (2, 'Auto-avaliação do professor', 'Auto-avaliação professor');
INSERT INTO categoriaquestionario (id,descricao,nome ) values (3, 'Avaliação da turma', 'Avaliação turma');
INSERT INTO categoriaquestionario (id,descricao,nome ) values (4, 'Avaliação do professor', 'Avaliação professor');


/*periodos*/
insert into periodo (id, nome) values (1, '1° Período');
insert into periodo (id, nome) values (2, '2° Período');
/*periodos*/

/*Telas*/
INSERT INTO tela (id, nome, nomeexibicao) values (1,  'questionario',	'Questionário');
INSERT INTO tela (id, nome, nomeexibicao) values (2,  'categoriaquestionario', 'Categoria Questionário');
INSERT INTO tela (id, nome, nomeexibicao) values (3,  'perfil', 'Perfil');
INSERT INTO tela (id, nome, nomeexibicao) values (4,  'pessoa', 'Pessoa');
INSERT INTO tela (id, nome, nomeexibicao) values (5,  'aluno', 'Aluno');
INSERT INTO tela (id, nome, nomeexibicao) values (6,  'professor', 'Professor');
INSERT INTO tela (id, nome, nomeexibicao) values (7,  'departamento', 'Departamento');
INSERT INTO tela (id, nome, nomeexibicao) values (8,  'curso', 'Curso');
INSERT INTO tela (id, nome, nomeexibicao) values (9,  'turma', 'Turma');
INSERT INTO tela (id, nome, nomeexibicao) values (10, 'disciplina', 'Disciplina');
INSERT INTO tela (id, nome, nomeexibicao) values (11, 'resultado', 'Resultados');
INSERT INTO tela (id, nome, nomeexibicao) values (12, 'questionarioresposta', 'Responder');
INSERT INTO tela (id, nome, nomeexibicao) values (13, 'cadastro-professor', 'Cadastro');
INSERT INTO tela (id, nome, nomeexibicao) values (14, 'cadastro-aluno', 'Cadastro');

/*Telas*/

/*tipo pergunta*/
INSERT INTO tipopergunta (id, descricao, nome) values (1, 'Descritiva', 'Descritiva');
INSERT INTO tipopergunta (id, descricao, nome) values (2, 'Múltipla escolha', 'Múltipla escolha');
/*tipo pergunta*/