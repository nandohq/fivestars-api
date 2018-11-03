# fivestars-api
API Rest de cadastro de hóspedes e registro de check-in

<h2>HÓSPEDES</h2>

<h3>Cadastro de hóspedes</h3>
<p>Para cadastrar um hóspede, faça uma requisição <strong>POST</strong> em <strong>/hospedes</strong> com o seguinte corpo JSON:</p>
<p>{ "nome" : "Nome do Hóspede", "documento" : "CPF em formato válido", "telefone" : "(99)99999-9999" }</p>

<h3>Consulta de um único hóspede</h3>
<p>Para obter os dados de um hóspede, faça uma requisição <strong>GET</strong> em <strong>/hospedes/{id}</strong> (em que "{id}" representa o código do hóspede desejado (ex.: /hospedes/5)).</p>

<h3>Edição de um hóspede</h3>
<p>Para atualizar os dados de um hóspede, faça uma requisição <strong>PUT</strong> em <strong>/hospedes/{id}</strong> (em que "{id}" representa o código do hóspede desejado (ex.: /hospedes/5)), com o mesmo formato JSON requerido para o cadastro</p>

<h3>Exclusão de um hóspede</h3>
<p>Para excluir um hóspede, faça uma requisição <strong>DELETE</strong> em <strong>/hospedes/{id}</strong> (em que "{id}" representa o código do hóspede desejado (ex.: /hospedes/5)).</p>

<h3>Lista/filtragem de hospedes</h3>
<p>Para obter uma lista de hóspedes, faça uma requisição <strong>GET</strong> em <strong>/hospedes</strong>. Para filtrar os resultados, adicione à URL os parâmetros <strong>nome</strong> (/hospedes?nome=Carlos), <strong>documento</strong> (/hospedes?documento=962.590.675-42), e/ou <strong>telefone</strong> (/hospedes?telefone=(99)99999-9999).</p>


<h2>CHECK-IN</h2>

<h3>Registro de check-in</h3>
<p>Para registrar o check-in de um hóspede, faça uma requisição <strong>POST</strong> em <strong>/checkin</strong> com o seguinte corpo JSON:</p>
<p>{ "dataHoraEntrada" : "2018-10-31T15:20:00", "dataHoraSaida" : "2018-11-08T12:00:00", "temVeiculo": false, "hospede": 5 }</p>

<h3>Consulta de um check-in</h3>
<p>Para obter os dados de um determinado check-in, faça uma requisição <strong>GET</strong> em <strong>/checkin/{id}</strong> (em que "{id}" representa o código do check-in desejado (ex.: /checkin/5)).</p>

<h3>Lista/filtragem de dados do check-in</h3>
<p>Para obter uma lista de check-ins, faça uma requisição <strong>GET</strong> em <strong>/checkin</strong>. Para filtrar os resultados, adicione à URL o parâmetro <strong>presentes</strong> (/checkin?presentes=true); uma lista será retornada com os hóspedes ainda presentes no hotel (true) ou que já completaram a estadia (false). A lista retornará os dados com o seguinte formato JSON:</p>
<p>{ "nome" : "Nome do Hóspede", "documento" : "CPF em formato válido", "valor": "valor total da estadia" }</p>

<h2>BUILD</h2>
<p>Para gerar um arquivo <strong>*.jar</strong> e executá-lo exterior à IDE, importe o projeto no <strong>Sprint Tool Suíte</strong>, clique com o botão direito sobre ele, selecione a opção <strong>Run as</strong> e, depois, <strong>Maven build...</strong>. Na janela que ser abrirá, no campo <strong>Goals</strong>, informe <strong>package</strong> e, finalmente, clique no botão <strong>Run</strong>.</p>
<p>Aguarde o processo de criação; o arquivo será gerado na pasta target, dentro do projeto.</p>
<p>Para executar o arquivo, basta clicá-lo duas vezes e realizar as requisições via Postma ou via browser</p>

<h2>CONSIDERAÇÕES</h2>
<p>Este projeto foi configurado para uso do banco <strong>PostgreSQL</strong>. Atualize o arquivo <strong>application.properties</strong> conforme o seu SGBD/credenciais. As tabelas serão criadas automaticamente através do framework de migração <strong>FlyWay</strong></p>
