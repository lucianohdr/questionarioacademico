/*Inserindo tipos de usuários no sistema*/

INSERT INTO tipousuario (id,descricao,nome,observacao) values (1, 'Administrador do Sistema', 'Administrador', 'Mantem avaliações, usuários e permissões');
INSERT INTO tipousuario (id,descricao,nome,observacao) values (2, 'Coordenador do Curso', 'Coordenador', 'Verifica resultados das avaliações do seu curso');
INSERT INTO tipousuario (id,descricao,nome,observacao) values (3, 'Chefe de Departamento', 'Chefe', 'Verifica resultados das avaliações dos cursos do departamento');
INSERT INTO tipousuario (id,descricao,nome,observacao) values (4, 'Professor', 'Professor', 'Realiza auto-avaliação do professor, avaliação da turma, verifica resultados da avaliação da sua disciplina');
INSERT INTO tipousuario (id,descricao,nome,observacao) values (4, 'Aluno', 'Aluno', 'Realiza auto-avaliação do aluno e avaliação do professor');