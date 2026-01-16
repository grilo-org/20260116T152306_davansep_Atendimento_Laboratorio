# Atendimento de laboratório

Essa API REST faz a persistência de um atendimento para um paciente que está no local.

#
Tabela de conteúdos
=================
   * [Introdução](#introdução)
   * [Metodos](#metodos)
   * [Respostas](#respostas)
   * [Como usar](#como-usar)
      * [Pré-requisitos](#pre-requisitos)
      * [Testes](#testes)
   * [Tecnologias](#tecnologias)

**Recursos disponíveis para acesso via API:**
* [Pacientes](#pacientes) 
	* [ <a href="#cadastrar">Cadastrar</a> | <a href="#buscar-todos">BuscarTodos</a> | <a href="#buscar-por-id">BuscarPorId</a> | <a href="#atualizar">Atualizar</a> ]
* [Serviços](#servicos)
	* [ <a href="#cadastrar">Cadastrar</a> | <a href="#buscar-todos">BuscarTodos</a> | <a href="#buscar-por-id">BuscarPorId</a> | <a href="#atualizar">Atualizar</a> ]
* [Atendimentos](#atendimentos) 
	* [ <a href="#incluir-para-paciente">IncluirParaPaciente</a> | <a href="#obter-por-paciente">ObterPorPaciente</a> | <a href="#bucar-por-id-atendimento">BuscarPorIdAtendimento</a> | <a href="#cancelar-atendimento">CancelarAtendimento</a> ]
#

## [Introdução](#introdução)
<br/>

Esta **API REST** foi desenvolvida com a linguagem de programação **JAVA 11** e com os frameworks **Spring Boot + Hibernate** para facilitar e agilizar os processos de injeçôes de dependências, consultas, uso de bibliotecas e atualizações de dados, entre outros processos necessários para o desenvolvimento desta aplicação. Para o armazenamento e gerenciamento de dados foi utilizado o **H2 Database** que é um banco de dados relacional que conta com suporte para bancos de dados persistentes e na memória. Todos os testes e requisições foram feitos com o **Postman** e pelo **Swagger**.

Sabendo que o intuito dessa API REST é persistir um atendimento para um paciente, identifiquei algumas necessidades que não foram especificadas no documento do teste, por exemplo, cadastro de paciente e cadastro dos serviços que são prestados no local do atendimento.

Criei uma **classe Paciente**, onde solicitamos os seguintes dados: nome, sexo, idade, endereço, telefone e e-mail. Assim que o cadastro é realizado com sucesso, também é gerado um código de paciente, que é um **identificador único universal UUID/GUID** com 32 dígitos hexadecimais, aumentando a confiabilidade na consulta dos dados, a segurança e desencorajando possíveis ataques por numeração.<br/>
Criei também a **classe Serviços** e para fazer o cadastro de um serviço, é preciso colocar a descrição do procedimento e o valor, também é gerado um identificador de serviço, tipo Long.

Com esses dados já armazenados no banco e podendo serem gerenciados separadamente, conseguimos uma organização e agilidade nos processos.

### Para criar um novo atendimento para um paciente é necessario informar os seguintes dados: <br/>
● identificador_paciente: código gerado ao finalizar o cadastro do paciente;<br/>
● tipo-atendimento: Pode conter os valores ELETIVO ou URGÊNCIA;<br/>
● data_atendimento": Data e hora do atendimento realizado;<br/>
● local: nome do local (hospital/clínica/laboratório) do atendimento;<br/>
● serviços: identificador do serviço.

Assim que o atendimento é incluído com sucesso, também é gerado um código de atendimento, que é um **identificador único universal UUID/GUID** com 32 dígitos hexadecimais.

<br/>

## [Métodos](#metodos)

Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| `POST` | Utilizado para incluir um novo registro. |
| `GET` | Obtem informações de um ou mais registros. |
| `PUT` | Atualiza dados de um registro ou altera sua situação. |
| `POST` | Cancela um atendimento do sistema. |

<br/>

## [Respostas](#respostas)

| Código | Descrição |
|---|---|
| `200` | Requisição executada com sucesso (ok).|
| `201` | Criação realizada com sucesso (created).|
| `400` | Erro na requisição, validação ou os campos informados não existem no sistema (bad Request).|
| `404` | Registro pesquisado não encontrado (Not found).|
<br/>

# ✅ [Como usar](#como-usar)

## [Pré-requisitos e como rodar a aplicação/testes](#pre-requisitos)

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com/downloads), [JRE/ JDK 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html). 
Esta aplicação foi desenvolvida e executada com a IDE [Eclipse](https://www.eclipse.org/downloads/), porém, você pode utilizar uma IDE que suporte a linguagem Java, que preferir.

### 🎲 Puxando o código para a sua máquina

```bash
# Clone este repositório do GitHub
$ git clone <https://github.com/davansep/Atendimento_Laboratorio>

```
<br/>

# [Testes](#testes)

## OPÇÕES PARA REALIZAR OS TESTES

**SWAGGER**<br/>
Depois de clonar o repositório, abra o arquivo na IDE que desejar, execute a aplicação pelo arquivo **AtendimentoLaboratorioApplication** que deve rodar sem erros e acesse o seguinte link: http://localhost:8080/swagger-ui/#/.

<br/>

**POSTMAN OU DIRETAMENTE NO H2 DATABASE**<br/>
Depois de clonar o repositório, abra o arquivo na IDE que desejar, execute a aplicação pelo arquivo **AtendimentoLaboratorioApplication** que deve rodar sem erros, conecte ao H2 Database acessando: http://localhost:8080/h2/, segue abaixo configurações do banco de dados que consta no arquivo application.properties.

        spring.h2.console.enabled=true
        spring.h2.console.path=/h2

        spring.datasource.url=jdbc:h2:~/test
        spring.datasource.driverClassName=org.h2.Driver
        spring.datasource.username=sa
        spring.datasource.password=

        spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true

#

## FORMATAÇÃO JSON DE CONSULTA E RESPOSTAS

## [Pacientes](#pacientes) [/pacientes]

### [Novo](#cadastrar) (Create) [POST]

+ Request (http://localhost:8080/pacientes)

    + Body

            {
                "nome": "PACIENTE 3",
                "sexo": "Feminino",
                "idade": 32,
                "endereco": "Av Brasil, 3000",
                "telefone": "12345678912",
                "email": "paciente3@gmail.com"
            }

+ Response Status **201 - CREATED** (application/json)

    + Body

            {
                "nome": "PACIENTE 3",
                "sexo": "Feminino",
                "idade": 32,
                "endereco": "Av Brasil, 3000",
                "telefone": "12345678912",
                "email": "paciente3@gmail.com",
                "identificador_paciente": "56246ee9-9de7-4508-bd37-5811bfe13e3a"
            }


+ Response Status **400 - Bad Request** (application/json)
 Erro na requisição, validação ou campos.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 400,
                "error": "Bad Request",
                "message": "JSON parse error: ...",
                "path": "/pacientes"
            }

+ Response Status **404 - Not found** (application/json)
 Quando o registro não foi encontrado.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 404,
                "error": "Not Found",
                "message": "JSON parse error: ...",
                "path": "/pacientes"
            }
<br/>

### [Listar Todos](#buscar-todos) (List) [GET]

+ Request (http://localhost:8080/pacientes)

+ Response Status **200 - OK** (application/json)

       [
            {
                "nome": "PACIENTE 1",
                "sexo": "Feminino",
                "idade": 32,
                "endereco": "Av Brasil, 1000",
                "telefone": "12345678912",
                "email": "paciente1@gmail.com",
                "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
            },
            {
                "nome": "PACIENTE 2",
                "sexo": "Masculino",
                "idade": 42,
                "endereco": "Av Brasil, 2000",
                "telefone": "96325874123",
                "email": "paciente2@gmail.com",
                "identificador_paciente": "f8598c15-25d0-4614-9af5-9d9406c6bd46"
            }
        ]
<br/>

### [Obter Um](#buscar-por-id) (List) [GET /pacientes/paciente/{id}]

+ Request (http://localhost:8080/pacientes/paciente/f3b8a4b0-7c29-41b9-abba-f2d5e320d981)

+ Response Status **200 - OK** (application/json)

            {
                "nome": "PACIENTE 1",
                "sexo": "Feminino",
                "idade": 32,
                "endereco": "Av Brasil, 1000",
                "telefone": "12345678912",
                "email": "paciente1@gmail.com",
                "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
            }
<br/>

### [Editar](#atualizar) (Update) [PUT /pacientes/paciente/]

+ Request (http://localhost:8080/pacientes/paciente/)

    + Body

            {
                "nome": "PACIENTE 3",
                "sexo": "Feminino",
                "idade": 22,
                "endereco": "Av Brasil, 3000",
                "telefone": "12345678912",
                "email": "paciente3@gmail.com",
                "identificador_paciente": "56246ee9-9de7-4508-bd37-5811bfe13e3a"
            }

+ Response Status **200 - OK** (application/json)

    + Body

           {
                "nome": "PACIENTE 3",
                "sexo": "Feminino",
                "idade": 22,
                "endereco": "Av Brasil, 3000",
                "telefone": "12345678912",
                "email": "paciente3@gmail.com",
                "identificador_paciente": "56246ee9-9de7-4508-bd37-5811bfe13e3a"
            }

+ Response Status **400 - Bad Request** (application/json)
 Erro na requisição, validação ou campos.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 400,
                "error": "Bad Request",
                "message": "JSON parse error: ...",
                "path": "/pacientes"
            }

+ Response Status **404 - Not found** (application/json)
 Quando o registro não foi encontrado.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 404,
                "error": "Not Found",
                "message": "JSON parse error: ...",
                "path": "/pacientes"
            }

<br/>
<br/>

## Serviços [/servicos]

### [Novo](#cadastrar) (Create) [POST]

+ Request (http://localhost:8080/servicos)

    + Body

            {
                "descricao_procedimento": "DENTISTA",
                "valor_servico": 83.00
            }

+ Response Status **201 - CREATED** (application/json)

    + Body

            {
                "id_servico": 4,
                "descricao_procedimento": "DENTISTA",
                "valor_servico": 83.0
            }


+ Response Status **400 - Bad Request** (application/json)
 Erro na requisição, validação ou campos.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 400,
                "error": "Bad Request",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }

+ Response Status **404 - Not found** (application/json)
 Quando o registro não foi encontrado.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 404,
                "error": "Not Found",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }
<br/>

### [Listar Todos](#buscar-todos) (List) [GET]

+ Request (http://localhost:8080/servicos)

+ Response Status **200 - OK** (application/json)

       [
            {
                "id_servico": 1,
                "descricao_procedimento": "Exame de sangue",
                "valor_servico": 103.0
            },
            {
                "id_servico": 2,
                "descricao_procedimento": "Consulta",
                "valor_servico": 50.0
            },
            {
                "id_servico": 3,
                "descricao_procedimento": "Retorno",
                "valor_servico": 30.0
            }
        ]
<br/>

### [Obter Um](#buscar-por-id) (List) [GET /servicos/{id_servico}]

+ Request (http://localhost:8080/servicos/4)

+ Response Status **200 - OK** (application/json)

            {
                "id_servico": 4,
                "descricao_procedimento": "Dentista",
                "valor_servico": 62.0
            }
<br/>

### [Editar](#atualizar) (Update) [PUT /servicos/servico/]

+ Request (http://localhost:8080/servicos/servico/)

    + Body

            {
                "id_servico": 4,
                "descricao_procedimento": "Dentistaa",
                "valor_servico": 62.0
            }

+ Response Status **200 - OK** (application/json)

    + Body

           {
                "id_servico": 4,
                "descricao_procedimento": "Dentistaa",
                "valor_servico": 62.0,
           }

+ Response Status **400 - Bad Request** (application/json)
 Erro na requisição, validação ou campos.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 400,
                "error": "Bad Request",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }

+ Response Status **404 - Not found** (application/json)
 Quando o registro não foi encontrado.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 404,
                "error": "Not Found",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }
<br/><br/>

## Atendimentos [/atendimentos](#atendimentos)

### [Incluir um atendimento para um paciente](#incluir-para-paciente) (Create) [POST /atendimentos/paciente/{codigo_paciente}]

+ Request (http://localhost:8080/atendimentos/paciente/f3b8a4b0-7c29-41b9-abba-f2d5e320d981)

    + Body

            {
                "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981",
                "tipo_atendimento": "URGÊNCIA",
                "local": "HOSPITAL",
                "servicos": [{
                    "id_servico": 2
                    },{
                    "id_servico": 1
                    }]
            }

+ Response Status **201 - CREATED** (application/json)

    + Body

            {
                "paciente": {
                    "nome": null,
                    "sexo": null,
                    "idade": null,
                    "endereco": null,
                    "telefone": null,
                    "email": null,
                    "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
                },
                "local": "HOSPITAL",
                "servicos": [
                    {
                        "id_servico": 2,
                        "descricao_procedimento": null,
                        "valor_servico": null
                    },
                    {
                        "id_servico": 1,
                        "descricao_procedimento": null,
                        "valor_servico": null
                    }
                ],
                "cancelado": null,
                "codigo_atendimento": "d12389db-bc3f-4df3-86fd-a89a0db9bbb0",
                "tipo_atendimento": "URGÊNCIA",
                "data_atendimento": "08/12/2021 14:50:02-03:00"
            }


+ Response Status **400 - Bad Request** (application/json)
 Erro na requisição, validação ou campos.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 400,
                "error": "Bad Request",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }

+ Response Status **404 - Not found** (application/json)
 Quando o registro não foi encontrado.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 404,
                "error": "Not Found",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }
<br/>

### [Obter os atendimentos deste paciente](#obter-por-paciente) (List) [GET /atendimentos/pacientes/{codigo_paciente}]

+ Request (http://localhost:8080/atendimentos/pacientes/f3b8a4b0-7c29-41b9-abba-f2d5e320d981)

+ Response Status **200 - OK** (application/json)

       [
            {
                "paciente": {
                    "nome": "PACIENTE 1",
                    "sexo": "Feminino",
                    "idade": 32,
                    "endereco": "Av Brasil, 1000",
                    "telefone": "12345678912",
                    "email": "paciente1@gmail.com",
                    "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
                },
                "local": "HOSPITAL",
                "servicos": [
                    {
                        "id_servico": 2,
                        "descricao_procedimento": "Consulta",
                        "valor_servico": 50.0
                    },
                    {
                        "id_servico": 1,
                        "descricao_procedimento": "Exame de sangue",
                        "valor_servico": 103.0
                    }
                ],
                "cancelado": null,
                "codigo_atendimento": "d12389db-bc3f-4df3-86fd-a89a0db9bbb0",
                "tipo_atendimento": "URGÊNCIA",
                "data_atendimento": "08/12/2021 14:50:02-03:00"
            }
        ]
<br/>

### [Obter informações por atendimento](#bucar-por-id-atendimento) (List) [GET /atendimentos/atendimento/{codigo_atendimento}]

+ Request (http://localhost:8080/atendimentos/atendimento/d12389db-bc3f-4df3-86fd-a89a0db9bbb0)

+ Response Status **200 - OK** (application/json)

       [
            {
                "paciente": {
                    "nome": "PACIENTE 1",
                    "sexo": "Feminino",
                    "idade": 32,
                    "endereco": "Av Brasil, 1000",
                    "telefone": "12345678912",
                    "email": "paciente1@gmail.com",
                    "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
                },
                "local": "HOSPITAL",
                "servicos": [
                    {
                        "id_servico": 2,
                        "descricao_procedimento": "Consulta",
                        "valor_servico": 50.0
                    },
                    {
                        "id_servico": 1,
                        "descricao_procedimento": "Exame de sangue",
                        "valor_servico": 103.0
                    }
                ],
                "cancelado": null,
                "codigo_atendimento": "d12389db-bc3f-4df3-86fd-a89a0db9bbb0",
                "tipo_atendimento": "URGÊNCIA",
                "data_atendimento": "08/12/2021 14:50:02-03:00"
            }
        ]
<br/>

### Alterar um atendimento (Update) [PUT /atendimentos/atendimento/]

+ Request (http://localhost:8080/atendimentos/atendimento/)

    + Body

            {
                "paciente": {
                    "nome": "PACIENTE 1",
                    "sexo": "Feminino",
                    "idade": 32,
                    "endereco": "Av Brasil, 1000",
                    "telefone": "12345678912",
                    "email": "paciente1@gmail.com",
                    "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
                },
                "local": "CLINICA",
                "servicos": [
                    {
                        "id_servico": 2,
                        "descricao_procedimento": "Consulta",
                        "valor_servico": 50.0
                    },
                    {
                        "id_servico": 1,
                        "descricao_procedimento": "Exame de sangue",
                        "valor_servico": 103.0
                    }
                ],
                "cancelado": null,
                "codigo_atendimento": "d12389db-bc3f-4df3-86fd-a89a0db9bbb0",
                "tipo_atendimento": "URGÊNCIA",
                "data_atendimento": "08/12/2021 14:50:02-03:00"
            }

+ Response Status **200 - OK** (application/json)

    + Body

           {
                "paciente": {
                    "nome": "PACIENTE 1",
                    "sexo": "Feminino",
                    "idade": 32,
                    "endereco": "Av Brasil, 1000",
                    "telefone": "12345678912",
                    "email": "paciente1@gmail.com",
                    "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
                },
                "local": "CLINICA",
                "servicos": [
                    {
                        "id_servico": 2,
                        "descricao_procedimento": "Consulta",
                        "valor_servico": 50.0
                    },
                    {
                        "id_servico": 1,
                        "descricao_procedimento": "Exame de sangue",
                        "valor_servico": 103.0
                    }
                ],
                "cancelado": null,
                "codigo_atendimento": "d12389db-bc3f-4df3-86fd-a89a0db9bbb0",
                "tipo_atendimento": "URGÊNCIA",
                "data_atendimento": "08/12/2021 14:50:02-03:00"
            }

+ Response Status **400 - Bad Request** (application/json)
 Erro na requisição, validação ou campos.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 400,
                "error": "Bad Request",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }

+ Response Status **404 - Not found** (application/json)
 Quando o registro não foi encontrado.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 404,
                "error": "Not Found",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }
<br/>

### [Cancelar um atendimento](#cancelar-atendimento) (Cancelar) [POST /atendimentos/atendimento/{codigo_atendimento}]

+ Request (http://localhost:8080/atendimentos/atendimento/d12389db-bc3f-4df3-86fd-a89a0db9bbb0)

+ Response Status **200 - OK** (application/json)

    + Body

            {
                "paciente": {
                    "nome": null,
                    "sexo": null,
                    "idade": null,
                    "endereco": null,
                    "telefone": null,
                    "email": null,
                    "identificador_paciente": "f3b8a4b0-7c29-41b9-abba-f2d5e320d981"
                },
                "local": "HOSPITAL",
                "servicos": [
                    {
                        "id_servico": 2,
                        "descricao_procedimento": null,
                        "valor_servico": null
                    },
                    {
                        "id_servico": 1,
                        "descricao_procedimento": null,
                        "valor_servico": null
                    }
                ],
                "cancelado": true,
                "codigo_atendimento": "d12389db-bc3f-4df3-86fd-a89a0db9bbb0",
                "tipo_atendimento": "URGÊNCIA",
                "data_atendimento": "08/12/2021 14:50:02-03:00"
            }


+ Response Status **400 - Bad Request** (application/json)
 Erro na requisição, validação ou campos.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 400,
                "error": "Bad Request",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }

+ Response Status **404 - Not found** (application/json)
 Quando o registro não foi encontrado.

    + Body

            {
                "timestamp": "2021-12-08T17:30:57.856+00:00",
                "status": 404,
                "error": "Not Found",
                "message": "JSON parse error: ...",
                "path": "/servicos"
            }
<br/>

# 💻 [Tecnologias](#tecnologias)

### **- IDE:** <br/>

[![Eclipse](https://img.shields.io/badge/Eclipse-2d2056?style=for-the-badge&logo=Eclipse&logoColor=f99501)](https://www.eclipse.org)

### **- Linguagens de programação:**
<div style="display: inline_block">
  <img align="center" alt="Java" height="50" width="50" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg">

<br/>

### **- Frameworks:**
<div style="display: inline_block">
  <img align="center" alt="spring" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg"> 
  
  <br/>

[![Hibernate](https://img.shields.io/badge/Hibernate-59666c?style=for-the-badge&logo=Hibernate&logoColor=ffffff)](https://hibernate.org/)

<br/>

### **- Documentações e Testes:**


[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=ffffff)](https://www.postman.com/)
[![Swagger](https://img.shields.io/badge/Swagger-green?style=for-the-badge&logo=Swagger&logoColor=ffffff)](https://swagger.io/)

<br/>

### **- Database:**
[![H2_Database](https://img.shields.io/badge/H2_Database-0005b4?style=for-the-badge&logo=H2Database&logoColor=ffffff)](http://h2database.com/html/main.html)

<br/>

# 👩 Author

<table>
	<tr>
		<td align="center">
			<a href="https://github.com/davansep">
				<img
					width="100px"
					height="auto"
					src="https://avatars.githubusercontent.com/u/81379748?v=4"
					alt="Priscila Davanse"
				/>
				<br />
				<sub>
					<b>Priscila Davanse <br/> (Ela/ Dela)</b>
				</sub>
			</a>
		</td>
