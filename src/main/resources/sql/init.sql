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

/*criando uma base de alunos, professores, turmas e cursos*/

/*professores*/
INSERT INTO usuario (id, login, senha) values (1, 'user1', 'senha');
INSERT INTO usuario (id, login, senha) values (2, 'user2', 'senha');
INSERT INTO usuario (id, login, senha) values (3, 'user3', 'senha');
INSERT INTO usuario (id, login, senha) values (4, 'user4', 'senha');
INSERT INTO usuario (id, login, senha) values (5, 'user5', 'senha');
INSERT INTO usuario (id, login, senha) values (6, 'user6', 'senha');

INSERT INTO pessoa (id, usuario_id, nome, email) values (1, 1, 'João da silva', 'joao@joao.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (2, 2, 'Pedro da silva', 'pedro@pedro.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (3, 3, 'Carlos da silva', 'carlos@carlos.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (4, 4, 'Julio da silva', 'julios@julio.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (5, 5, 'Emilio da silva', 'emilio@emilio.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (6, 6, 'Mateus da silva', 'mateus@mateus.com');

INSERT INTO professor (id, pessoa_id, matricula) values (1, 1, '12345');
INSERT INTO professor (id, pessoa_id, matricula) values (2, 2, '12345');
INSERT INTO professor (id, pessoa_id, matricula) values (3, 3, '12345');
INSERT INTO professor (id, pessoa_id, matricula) values (4, 4, '12345');
INSERT INTO professor (id, pessoa_id, matricula) values (5, 5, '12345');
INSERT INTO professor (id, pessoa_id, matricula) values (6, 6, '12345');

insert into usuario_perfil (id_usuario, id_perfil) values (1, 1);/*adm*/
insert into usuario_perfil (id_usuario, id_perfil) values (2, 2);/*coo*/
insert into usuario_perfil (id_usuario, id_perfil) values (3, 3);/*che*/
insert into usuario_perfil (id_usuario, id_perfil) values (4, 3);/*che*/
insert into usuario_perfil (id_usuario, id_perfil) values (5, 2);/*coo*/
insert into usuario_perfil (id_usuario, id_perfil) values (6, 4);
/*professores*/

/*departamentos*/
insert into departamento (id, professor_id, nome, descricao) values (1, 3,'Tecnologia', 'Departamento de Tecnologias');
insert into departamento (id, professor_id, nome, descricao) values (2, 4,'Administração', 'Departamento de Administração');
/*departamentos*/

/*Cursos*/
insert into curso (id, professor_id, departamento_id, nome, descricao) values (1, 2, 1, 'Sistemas de informação', 'Bacharelado em sistemas de informação');
insert into curso (id, professor_id, departamento_id, nome, descricao) values (2, 5, 2, 'Administração', 'Bacharelado em Administração');
/*Cursos*/

/*periodos*/
insert into periodo (id, nome) values (1, '1° Período');
insert into periodo (id, nome) values (2, '2° Período');
/*periodos*/

/*Turmas*/
insert into turma (id, curso_id, periodo_id, nome, descricao) values (1, 1, 1, 'SI1', 'Primeira turma de Sistemas de informação');
insert into turma (id, curso_id, periodo_id, nome, descricao) values (2, 2, 1, 'AD1', 'Primeira turma de Admistração');
/*Turmas*/

/*disciplinas*/
insert into disciplina(id, curso_id, professor_id, periodo_id, nome, descricao) values (1, 1, 6, 1, 'Calculo I', 'Discplina de calculo introdutório');
insert into disciplina(id, curso_id, professor_id, periodo_id, nome, descricao) values (2, 1, 5, 1, 'Metodologia', 'Discplina de Metodologia');
insert into disciplina(id, curso_id, professor_id, periodo_id, nome, descricao) values (3, 2, 2, 1, 'Etica', 'Discplina de Ética');
insert into disciplina(id, curso_id, professor_id, periodo_id, nome, descricao) values (4, 2, 3, 1, 'Gerenciamento', 'Discplina de Gerenciamento');
/*disciplinas*/

/*alunos*/
INSERT INTO usuario (id, login, senha) values (7 , 'user7', 'senha');
INSERT INTO usuario (id, login, senha) values (8 , 'user8', 'senha');
INSERT INTO usuario (id, login, senha) values (9 , 'user9', 'senha');
INSERT INTO usuario (id, login, senha) values (10, 'user10', 'senha');
INSERT INTO usuario (id, login, senha) values (11, 'user11', 'senha');
INSERT INTO usuario (id, login, senha) values (12, 'user12', 'senha');
INSERT INTO usuario (id, login, senha) values (13, 'user13', 'senha');
INSERT INTO usuario (id, login, senha) values (14, 'user14', 'senha');
INSERT INTO usuario (id, login, senha) values (15, 'user15', 'senha');
INSERT INTO usuario (id, login, senha) values (16, 'user16', 'senha');
INSERT INTO usuario (id, login, senha) values (17, 'user17', 'senha');

INSERT INTO pessoa (id, usuario_id, nome, email) values (7 , 7 , 'Mateus da Souza', 'mateus@mateus.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (8 , 8 , 'Roberto da Santos', 'roberto@roberto.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (9 , 9 , 'João da Silveira', 'joao@joao.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (10, 10, 'Luciano da Cavalcanti', 'luciano@luciano.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (11, 11, 'Mateus dos Anjos', 'mateus@mateus.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (12, 12, 'Luciane da Rocha', 'luciane@luciane.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (13, 13, 'Eduardo da Santos', 'eduardo@eduardo.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (14, 14, 'diego da Silveira', 'diego@diego.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (15, 15, 'Andreia dos Anjos', 'andreia@andreia.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (16, 16, 'Maria da Rocha', 'maria@maria.com');
INSERT INTO pessoa (id, usuario_id, nome, email) values (17, 17, 'Marina Emiliano', 'marina@marina.com');

insert into usuario_perfil (id_usuario, id_perfil) values (7 , 5);
insert into usuario_perfil (id_usuario, id_perfil) values (8 , 5);
insert into usuario_perfil (id_usuario, id_perfil) values (9 , 5);
insert into usuario_perfil (id_usuario, id_perfil) values (10, 5);
insert into usuario_perfil (id_usuario, id_perfil) values (11, 5);
insert into usuario_perfil (id_usuario, id_perfil) values (12, 5);
insert into usuario_perfil (id_usuario, id_perfil) values (13, 5);
insert into usuario_perfil (id_usuario, id_perfil) values (14, 5);
insert into usuario_perfil (id_usuario, id_perfil) values (15, 5);
insert into usuario_perfil (id_usuario, id_perfil) values (16, 5);
insert into usuario_perfil (id_usuario, id_perfil) values (17, 5);

INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (1 , 7 , 1, 1, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (2 , 8 , 1, 1, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (3 , 9 , 1, 1, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (4 , 10, 1, 1, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (5 , 11, 1, 1, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (6 , 12, 2, 2, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (7 , 13, 2, 2, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (8 , 14, 2, 2, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (9 , 15, 2, 2, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (10, 16, 2, 2, '123'); 
INSERT INTO aluno (id, pessoa_id, curso_id, turma_id, ra) values (11, 17, 2, 2, '123'); 
/*alunos*/
