package com.ejt.toaquimobile;

/**
 * Created by Eric Gustavo on 09/06/2016.
 */
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Eric Gustavo on 31/03/2016.
 */
public class WebService {

    // 192.168.1.123

    private static String URL = "http://192.168.43.3:9090/ToAquiWebService/services/WebService?wsdl";
    private static String NOMESPACE = "http://webservice.ejt.com";

    //       /ToAquiWebService/services/WebService?wsdl http://192.168.43.47:9090
    public WebService(){

    }


    public void usuarioAtualizar(Usuario usuario) throws Exception {

        SoapObject soap = new SoapObject(NOMESPACE,"usuarioAtualizar");
        SoapObject soapUser = new SoapObject(NOMESPACE,"usuario");

        //soapUser.addProperty("usuario", usuario);
        soapUser.addProperty("id_user", usuario.getId_user());
        soapUser.addProperty("nome", usuario.getNome());
        //soapUser.addProperty("email", usuario.getEmail());
        soapUser.addProperty("senha", usuario.getSenha());

        soap.addSoapObject(soapUser);


        //soap.addProperty("usuario", usuario);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;


        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:usuarioAtualizar", envelope);



    }

    public void avaliacaoRemover(int id_user, int id_estabelecimento) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"avaliacaoRemover");

        soap.addProperty("id_user", id_user);
        soap.addProperty("id_estabelecimento", id_estabelecimento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:avaliacaoRemover", envelope);

    }

    public void eventoRemoverId(int id_evento) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"eventoRemoverId");

        soap.addProperty("id_evento", id_evento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:eventoRemoverId", envelope);

    }


    public void estabelecimentoRemoverId(int id_estabelecimento) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoRemoverId");

        soap.addProperty("id_estabelecimento", id_estabelecimento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:estabelecimentoRemoverId", envelope);
    }

    public void usuarioRemoverId(int id_user) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"usuarioRemoverId");

        soap.addProperty("id_user", id_user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:usuarioRemoverId", envelope);
    }

    public void estabelecimentoAtualizar(Estabelecimento estabelecimento) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoAtualizar");
        SoapObject soapEstab = new SoapObject(NOMESPACE,"estabelecimento");

        SoapObject end = new SoapObject(null,"endereco");
        end.addProperty("endereco", estabelecimento.getEndereco().getEndereco());
        end.addProperty("latitude", estabelecimento.getEndereco().getLatitude());
        end.addProperty("longitude", estabelecimento.getEndereco().getLongitude());

        SoapObject cnt = new SoapObject(null, "contato");
        cnt.addProperty("email", estabelecimento.getContato().getEmail());
        cnt.addProperty("celular", estabelecimento.getContato().getCelular());
        cnt.addProperty("telefone", estabelecimento.getContato().getTelefone());

        soapEstab.addSoapObject(end);
        soapEstab.addSoapObject(cnt);

        soapEstab.addProperty("id_estabelecimento", estabelecimento.getId_estabelecimento());
        soapEstab.addProperty("nome", estabelecimento.getNome());
        soapEstab.addProperty("descricao", estabelecimento.getDescricao());
        soapEstab.addProperty("imagem", estabelecimento.getImagem());
        soapEstab.addProperty("categoria",estabelecimento.getCategoria());
        soapEstab.addProperty("pagamento_visa",estabelecimento.getPagamento_visa());
        soapEstab.addProperty("pagamento_master",estabelecimento.getPagamento_master());
        soapEstab.addProperty("pagamento_hiper",estabelecimento.getPagamento_hiper());
        soapEstab.addProperty("pagamento_cielo",estabelecimento.getPagamento_cielo());
        soapEstab.addProperty("pagamento_outro", estabelecimento.getPagamento_outro());

        soap.addSoapObject(soapEstab);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:estabelecimentoAtualizar", envelope);

    }

    public void estabelecimentoCadastrar(Estabelecimento estabelecimento) throws Exception{
        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoCadastrar");
        SoapObject soapEstab = new SoapObject(NOMESPACE,"estabelecimento");

        SoapObject end = new SoapObject(null,"endereco");
        end.addProperty("endereco", estabelecimento.getEndereco().getEndereco());
        end.addProperty("latitude", estabelecimento.getEndereco().getLatitude());
        end.addProperty("longitude", estabelecimento.getEndereco().getLongitude());

        SoapObject cnt = new SoapObject(null, "contato");
        cnt.addProperty("email", estabelecimento.getContato().getEmail());
        cnt.addProperty("celular", estabelecimento.getContato().getCelular());
        cnt.addProperty("telefone", estabelecimento.getContato().getTelefone());

        soapEstab.addSoapObject(end);
        soapEstab.addSoapObject(cnt);

        soapEstab.addProperty("id_estabelecimento", estabelecimento.getId_estabelecimento());
        soapEstab.addProperty("nome",estabelecimento.getNome());
        soapEstab.addProperty("descricao",estabelecimento.getDescricao());
        soapEstab.addProperty("imagem",estabelecimento.getImagem());
        soapEstab.addProperty("categoria",estabelecimento.getCategoria());
        soapEstab.addProperty("pagamento_visa",estabelecimento.getPagamento_visa());
        soapEstab.addProperty("pagamento_master",estabelecimento.getPagamento_master());
        soapEstab.addProperty("pagamento_hiper",estabelecimento.getPagamento_hiper());
        soapEstab.addProperty("pagamento_cielo",estabelecimento.getPagamento_cielo());
        soapEstab.addProperty("pagamento_outro", estabelecimento.getPagamento_outro());
        soapEstab.addProperty("id_user", estabelecimento.getId_user());
        // soapEstab.addProperty("user", estabelecimento.getUser());

        soap.addSoapObject(soapEstab);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:estabelecimentoCadastrar", envelope);

    }

    public void avaliacaoCadastra(Avaliacao avaliacao) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"avaliacaoCadastra");
        SoapObject soapAvaliacao = new SoapObject(NOMESPACE,"avaliacao");

        soapAvaliacao.addProperty("id_user", avaliacao.getId_user());
        soapAvaliacao.addProperty("id_estabelecimento", avaliacao.getId_estabelecimento());
        soapAvaliacao.addProperty("nota", avaliacao.getNota());
        soapAvaliacao.addProperty("descricao", avaliacao.getDescricao());

        soap.addSoapObject(soapAvaliacao);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;


        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:avaliacaoCadastra", envelope);
    }

    public void avaliacaoAtualizar(Avaliacao avaliacao) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"avaliacaoAtualizar");
        SoapObject soapAvaliacao = new SoapObject(NOMESPACE,"avaliacao");

        soapAvaliacao.addProperty("id_user", avaliacao.getId_user());
        soapAvaliacao.addProperty("id_estabelecimento", avaliacao.getId_estabelecimento());
        soapAvaliacao.addProperty("nota", avaliacao.getNota());
        soapAvaliacao.addProperty("descricao", avaliacao.getDescricao());

        soap.addSoapObject(soapAvaliacao);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;


        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:avaliacaoAtualizar", envelope);
    }
    public void usuarioCadastrar(Usuario usuario) throws Exception {

            SoapObject soap = new SoapObject(NOMESPACE, "usuarioCadastrar");
            SoapObject soapUser = new SoapObject(NOMESPACE, "usuario");

            //soapUser.addProperty("usuario", usuario);
            soapUser.addProperty("id_user", usuario.getId_user());
            soapUser.addProperty("nome", usuario.getNome());
            soapUser.addProperty("email", usuario.getEmail());
            soapUser.addProperty("senha", usuario.getSenha());

            soap.addSoapObject(soapUser);

            //soap.addProperty("usuario", usuario);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soap);
            envelope.implicitTypes = true;


            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            httpTransportSE.call("urm:usuarioCadastrar", envelope);

            SoapFault resSoapFault = (SoapFault) envelope.getResponse();


    }

    public boolean avaliacaoExiste(int id_user, int id_estabelecimento) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"avaliacaoExiste");
        soap.addProperty("id_user", id_user);
        soap.addProperty("id_estabelecimento", id_estabelecimento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:avaliacaoExiste", envelope);

        SoapPrimitive reposta = (SoapPrimitive) envelope.getResponse();
        return Boolean.parseBoolean(reposta.toString());
    }



    public boolean usuarioLogin(String email, String senha) throws Exception{
        SoapObject soap = new SoapObject(NOMESPACE,"usuarioLogin");

        //SoapObject soapLoginEmail = new SoapObject(NOMESPACE, "email");
        soap.addProperty("email", email);

        //SoapObject soapLoginSenha = new SoapObject(NOMESPACE, "senha");
        soap.addProperty("senha", senha);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:usuarioLogin", envelope);



        SoapPrimitive reposta = (SoapPrimitive) envelope.getResponse();
        //boolean login = (boolean) envelope.getResponse();
        Log.i("Teste", "Entrou no EMbaixo do envelope!!!!");



        return Boolean.parseBoolean(reposta.toString());
    }

    public Usuario usuarioProcurarEmail(String email) throws Exception {
        Usuario user = new Usuario();

        SoapObject soap = new SoapObject(NOMESPACE,"usuarioProcurarEmail");
        soap.addProperty("email", email);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:usuarioProcurarEmail", envelope);

        SoapObject resposta =(SoapObject) envelope.getResponse();

        int id_user = Integer.parseInt(resposta.getProperty("id_user").toString());
        String nome = resposta.getProperty("nome").toString();
        String email1 = resposta.getProperty("email").toString();

        user.setId_user(id_user);
        user.setNome(nome);
        user.setEmail(email);

        return user;
    }


    //Estabelecimento

    public Evento eventoProcurarId(int id_evento) throws  Exception{
        Evento evento = new Evento();
        SoapObject soap = new SoapObject(NOMESPACE,"eventoProcurarId");
        soap.addProperty("id_evento", id_evento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:eventoProcurarId", envelope);

        SoapObject resultado = (SoapObject) envelope.getResponse();

        Endereco enderecoObj = new Endereco();
        Contato contatoObj = new Contato();

        int id_evento2 = Integer.parseInt(resultado.getProperty("id_evento").toString());
        int id_user = Integer.parseInt(resultado.getProperty("id_user").toString());
        String nome = resultado.getProperty("nome").toString();
        String local = resultado.getProperty("local").toString();
        String data_evento = resultado.getProperty("data_evento").toString();
        float valor_ingresso = Float.parseFloat(resultado.getProperty("valor_ingresso").toString());
        String descricao =  resultado.getProperty("descricao").toString();
        String imagem =  resultado.getProperty("imagem").toString();


        String endereco = resultado.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
        String contato = resultado.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

        String endCompleto[] = endereco.trim().split(";");
        String cntCompleto[] = contato.trim().split(";");


        String enderecoCerto = endCompleto[0];
        String latitude = endCompleto[1].replace(" ", "");
        String longitude = endCompleto[2].replace(" ", "");

        String email = cntCompleto[1].replace(" ", "");
        String celular = cntCompleto[0].replace(" ", "");
        String telefone = cntCompleto[2].replace(" ", "");

        enderecoObj.setEndereco(enderecoCerto);
        enderecoObj.setLatitude(latitude);
        enderecoObj.setLongitude(longitude);
        contatoObj.setTelefone(telefone);
        contatoObj.setCelular(celular);
        contatoObj.setEmail(email);

        evento.setContato(contatoObj);
        evento.setEndereco(enderecoObj);
        evento.setId_evento(id_evento2);
        evento.setNome(nome);
        evento.setLocal(local);
        evento.setData_evento(data_evento);
        evento.setValor_ingresso(valor_ingresso);
        evento.setDescricao(descricao);
        evento.setImagem(imagem);
        evento.setEndereco(enderecoObj);
        evento.setContato(contatoObj);
        evento.setId_user(id_user);

        return evento;
    }

    public Estabelecimento estabelecimentoProcurarId(int id_estabelecimento) throws Exception {
        Estabelecimento estab = new Estabelecimento();

        //Enviando
        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoProcurarId");
        soap.addProperty("id_estabelecimento", id_estabelecimento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:estabelecimentoProcurarId", envelope);

        //Recuperando
        SoapObject resultado = (SoapObject) envelope.getResponse();

        Endereco enderecoObj = new Endereco();
        Contato contatoObj = new Contato();

        int id_estab = Integer.parseInt(resultado.getProperty("id_estabelecimento").toString());
        int id_user = Integer.parseInt(resultado.getProperty("id_user").toString());
        String nome =  resultado.getProperty("nome").toString();

        String endereco = resultado.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
        String contato = resultado.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

        String endCompleto[] = endereco.trim().split(";");
        String cntCompleto[] = contato.trim().split(";");


        String enderecoCerto = endCompleto[0];
        String latitude = endCompleto[1].replace(" ", "");
        String longitude = endCompleto[2].replace(" ", "");

        String email = cntCompleto[1].replace(" ", "");
        String celular = cntCompleto[0].replace(" ", "");
        String telefone = cntCompleto[2].replace(" ", "");


        String categoria = resultado.getProperty("categoria").toString();
        String visa =  resultado.getProperty("pagamento_visa").toString();
        String master =  resultado.getProperty("pagamento_master").toString();
        String cielo =  resultado.getProperty("pagamento_cielo").toString();
        String hiper =  resultado.getProperty("pagamento_hiper").toString();
        String outro =  resultado.getProperty("pagamento_outro").toString();
        String descricao =  resultado.getProperty("descricao").toString();
        String imagem =  resultado.getProperty("imagem").toString();



        enderecoObj.setEndereco(enderecoCerto);
        enderecoObj.setLatitude(latitude);
        enderecoObj.setLongitude(longitude);
        contatoObj.setTelefone(telefone);
        contatoObj.setCelular(celular);
        contatoObj.setEmail(email);

        // Estabelecimento estab = new Estabelecimento(id_estab, nome, endereco, contato, categoria,visa,master,cielo,hiper,outro,descricao,imagem);

        estab.setNome(nome);
        estab.setId_estabelecimento(id_estab);
        estab.setCategoria(categoria);
        estab.setPagamento_visa(visa);
        estab.setPagamento_master(master);
        estab.setPagamento_cielo(cielo);
        estab.setPagamento_hiper(hiper);
        estab.setPagamento_outro(outro);
        estab.setDescricao(descricao);
        estab.setImagem(imagem);
        estab.setId_user(id_user);

        estab.setEndereco(enderecoObj);
        estab.setContato(contatoObj);



        return estab;
    }

    public ArrayList<Estabelecimento> estabelecimentoListarBuscaNome(String nome) throws Exception {
        ArrayList<Estabelecimento> listar = new ArrayList<Estabelecimento>();

        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoListarBuscaNome");
        soap.addProperty("nome", nome);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:estabelecimentoListarBuscaNome", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_estab = Integer.parseInt(soapObject.getProperty("id_estabelecimento").toString());
            String nome1 =  soapObject.getProperty("nome").toString();

            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");


            String categoria = soapObject.getProperty("categoria").toString();
            String visa =  soapObject.getProperty("pagamento_visa").toString();
            String master =  soapObject.getProperty("pagamento_master").toString();
            String cielo =  soapObject.getProperty("pagamento_cielo").toString();
            String hiper =  soapObject.getProperty("pagamento_hiper").toString();
            String outro =  soapObject.getProperty("pagamento_outro").toString();
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();



            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            // Estabelecimento estab = new Estabelecimento(id_estab, nome, endereco, contato, categoria,visa,master,cielo,hiper,outro,descricao,imagem);

            Estabelecimento estab = new Estabelecimento();

            estab.setNome(nome1);
            estab.setId_estabelecimento(id_estab);
            estab.setCategoria(categoria);
            estab.setPagamento_visa(visa);
            estab.setPagamento_master(master);
            estab.setPagamento_cielo(cielo);
            estab.setPagamento_hiper(hiper);
            estab.setPagamento_outro(outro);
            estab.setDescricao(descricao);
            estab.setImagem(imagem);


            estab.setEndereco(enderecoObj);
            estab.setContato(contatoObj);
            listar.add(estab);

        }


        return listar;
    }

    public ArrayList<Avaliacao> avaliacaoListar(int id_estabelecimento) throws Exception {
        ArrayList<Avaliacao> listar = new ArrayList<Avaliacao>();
        SoapObject soap = new SoapObject(NOMESPACE,"avaliacaoListar");
        soap.addProperty("id_estabelecimento", id_estabelecimento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:avaliacaoListar", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
        for (SoapObject soapObject: resposta) {
            Usuario user = new Usuario();
            //  Estabelecimento estabelecimento = new Estabelecimento();

            int nota = Integer.parseInt(soapObject.getProperty("nota").toString());
            int id_user = Integer.parseInt(soapObject.getProperty("id_user").toString());
            int id_estab = Integer.parseInt(soapObject.getProperty("id_estabelecimento").toString());
            String descricao = soapObject.getProperty("descricao").toString();
            String usuario = soapObject.getProperty("usuario").toString();
            String nome = usuario.replace("Usuario","").replace("id_user=0;", "").replace("email=null;","").replace("nome=","").replace("senha=null;", "").replace("}","").replace("{","").replace(" ","").replace(";","");

            Avaliacao avaliacao = new Avaliacao();

            user.setNome(nome);

            avaliacao.setDescricao(descricao);
            avaliacao.setNota(nota);
            avaliacao.setId_user(id_user);
            avaliacao.setId_estabelecimento(id_estabelecimento);
            avaliacao.setUsuario(user);

            listar.add(avaliacao);
        }
        return listar;
    }

    public ArrayList<Estabelecimento> estabelecimentoListarCadastrados(int id_user) throws Exception {
        ArrayList<Estabelecimento> listar = new ArrayList<Estabelecimento>();

        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoListarCadastrados");
        soap.addProperty("id_user", id_user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:estabelecimentoListarCadastrados", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_estab = Integer.parseInt(soapObject.getProperty("id_estabelecimento").toString());
            String nome =  soapObject.getProperty("nome").toString();

            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");


            String categoria = soapObject.getProperty("categoria").toString();
            String visa =  soapObject.getProperty("pagamento_visa").toString();
            String master =  soapObject.getProperty("pagamento_master").toString();
            String cielo =  soapObject.getProperty("pagamento_cielo").toString();
            String hiper =  soapObject.getProperty("pagamento_hiper").toString();
            String outro =  soapObject.getProperty("pagamento_outro").toString();
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();



            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            // Estabelecimento estab = new Estabelecimento(id_estab, nome, endereco, contato, categoria,visa,master,cielo,hiper,outro,descricao,imagem);

            Estabelecimento estab = new Estabelecimento();

            estab.setNome(nome);
            estab.setId_estabelecimento(id_estab);
            estab.setCategoria(categoria);
            estab.setPagamento_visa(visa);
            estab.setPagamento_master(master);
            estab.setPagamento_cielo(cielo);
            estab.setPagamento_hiper(hiper);
            estab.setPagamento_outro(outro);
            estab.setDescricao(descricao);
            estab.setImagem(imagem);


            estab.setEndereco(enderecoObj);
            estab.setContato(contatoObj);
            listar.add(estab);

        }


        return listar;
    }

    public ArrayList<Estabelecimento> estabelecimentoListarCadastradosPendentes(int id_user) throws Exception {
        ArrayList<Estabelecimento> listar = new ArrayList<Estabelecimento>();

        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoListarCadastradosPendentes");
        soap.addProperty("id_user", id_user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:estabelecimentoListarCadastradosPendentes", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_estab = Integer.parseInt(soapObject.getProperty("id_estabelecimento").toString());
            String nome =  soapObject.getProperty("nome").toString();

            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");


            String categoria = soapObject.getProperty("categoria").toString();
            String visa =  soapObject.getProperty("pagamento_visa").toString();
            String master =  soapObject.getProperty("pagamento_master").toString();
            String cielo =  soapObject.getProperty("pagamento_cielo").toString();
            String hiper =  soapObject.getProperty("pagamento_hiper").toString();
            String outro =  soapObject.getProperty("pagamento_outro").toString();
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();



            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            // Estabelecimento estab = new Estabelecimento(id_estab, nome, endereco, contato, categoria,visa,master,cielo,hiper,outro,descricao,imagem);

            Estabelecimento estab = new Estabelecimento();

            estab.setNome(nome);
            estab.setId_estabelecimento(id_estab);
            estab.setCategoria(categoria);
            estab.setPagamento_visa(visa);
            estab.setPagamento_master(master);
            estab.setPagamento_cielo(cielo);
            estab.setPagamento_hiper(hiper);
            estab.setPagamento_outro(outro);
            estab.setDescricao(descricao);
            estab.setImagem(imagem);


            estab.setEndereco(enderecoObj);
            estab.setContato(contatoObj);
            listar.add(estab);

        }


        return listar;
    }

    public ArrayList<Estabelecimento> estabelecimentoListar() throws Exception {
        ArrayList<Estabelecimento> listar = new ArrayList<Estabelecimento>();

        SoapObject soap = new SoapObject(NOMESPACE,"estabelecimentoListar");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:estabelecimentoListar", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_estab = Integer.parseInt(soapObject.getProperty("id_estabelecimento").toString());
            String nome =  soapObject.getProperty("nome").toString();

            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");


            String categoria = soapObject.getProperty("categoria").toString();
            String visa =  soapObject.getProperty("pagamento_visa").toString();
            String master =  soapObject.getProperty("pagamento_master").toString();
            String cielo =  soapObject.getProperty("pagamento_cielo").toString();
            String hiper =  soapObject.getProperty("pagamento_hiper").toString();
            String outro =  soapObject.getProperty("pagamento_outro").toString();
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();



            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            // Estabelecimento estab = new Estabelecimento(id_estab, nome, endereco, contato, categoria,visa,master,cielo,hiper,outro,descricao,imagem);

            Estabelecimento estab = new Estabelecimento();

            estab.setNome(nome);
            estab.setId_estabelecimento(id_estab);
            estab.setCategoria(categoria);
            estab.setPagamento_visa(visa);
            estab.setPagamento_master(master);
            estab.setPagamento_cielo(cielo);
            estab.setPagamento_hiper(hiper);
            estab.setPagamento_outro(outro);
            estab.setDescricao(descricao);
            estab.setImagem(imagem);


            estab.setEndereco(enderecoObj);
            estab.setContato(contatoObj);
            listar.add(estab);

        }


        return listar;
    }

    public ArrayList<Evento> eventoListarBuscaNome(String nome) throws Exception {
        ArrayList<Evento> lista = new ArrayList<Evento>();

        SoapObject soap = new SoapObject(NOMESPACE,"eventoListarBuscaNome");
        soap.addProperty("nome", nome);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:eventoListarBuscaNome", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_evento = Integer.parseInt(soapObject.getProperty("id_evento").toString());
            String nome1 = soapObject.getProperty("nome").toString();
            String local = soapObject.getProperty("local").toString();
            String data_evento = soapObject.getProperty("data_evento").toString();
            float valor_ingresso = Float.parseFloat(soapObject.getProperty("valor_ingresso").toString());
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();


            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");

            Evento evento = new Evento();
            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            evento.setId_evento(id_evento);
            evento.setNome(nome1);
            evento.setLocal(local);
            evento.setData_evento(data_evento);
            evento.setValor_ingresso(valor_ingresso);
            evento.setDescricao(descricao);
            evento.setImagem(imagem);
            evento.setEndereco(enderecoObj);
            evento.setContato(contatoObj);

            lista.add(evento);

        }

        return lista;
    }

    public ArrayList<Evento> eventoListarCadastrados(int id_user) throws Exception {
        ArrayList<Evento> lista = new ArrayList<Evento>();

        SoapObject soap = new SoapObject(NOMESPACE,"eventoListarCadastrados");
        soap.addProperty("id_user", id_user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:eventoListarCadastrados", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_evento = Integer.parseInt(soapObject.getProperty("id_evento").toString());
            String nome = soapObject.getProperty("nome").toString();
            String local = soapObject.getProperty("local").toString();
            String data_evento = soapObject.getProperty("data_evento").toString();
            float valor_ingresso = Float.parseFloat(soapObject.getProperty("valor_ingresso").toString());
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();


            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");

            Evento evento = new Evento();
            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            evento.setId_evento(id_evento);
            evento.setNome(nome);
            evento.setLocal(local);
            evento.setData_evento(data_evento);
            evento.setValor_ingresso(valor_ingresso);
            evento.setDescricao(descricao);
            evento.setImagem(imagem);
            evento.setEndereco(enderecoObj);
            evento.setContato(contatoObj);

            lista.add(evento);

        }

        return lista;
    }

    public ArrayList<Evento> eventoListarCadastradosPendentes(int id_user) throws Exception {
        ArrayList<Evento> lista = new ArrayList<Evento>();

        SoapObject soap = new SoapObject(NOMESPACE,"eventoListarCadastradosPendentes");
        soap.addProperty("id_user", id_user);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:eventoListarCadastradosPendentes", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_evento = Integer.parseInt(soapObject.getProperty("id_evento").toString());
            String nome = soapObject.getProperty("nome").toString();
            String local = soapObject.getProperty("local").toString();
            String data_evento = soapObject.getProperty("data_evento").toString();
            float valor_ingresso = Float.parseFloat(soapObject.getProperty("valor_ingresso").toString());
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();


            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");

            Evento evento = new Evento();
            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            evento.setId_evento(id_evento);
            evento.setNome(nome);
            evento.setLocal(local);
            evento.setData_evento(data_evento);
            evento.setValor_ingresso(valor_ingresso);
            evento.setDescricao(descricao);
            evento.setImagem(imagem);
            evento.setEndereco(enderecoObj);
            evento.setContato(contatoObj);

            lista.add(evento);

        }

        return lista;
    }

    public ArrayList<Evento> eventoListar() throws Exception {
        ArrayList<Evento> lista = new ArrayList<Evento>();

        SoapObject soap = new SoapObject(NOMESPACE,"eventoListar");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urm:eventoListar", envelope);

        Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();

        for (SoapObject soapObject: resposta) {
            Endereco enderecoObj = new Endereco();
            Contato contatoObj = new Contato();

            int id_evento = Integer.parseInt(soapObject.getProperty("id_evento").toString());
            String nome = soapObject.getProperty("nome").toString();
            String local = soapObject.getProperty("local").toString();
            String data_evento = soapObject.getProperty("data_evento").toString();
            float valor_ingresso = Float.parseFloat(soapObject.getProperty("valor_ingresso").toString());
            String descricao =  soapObject.getProperty("descricao").toString();
            String imagem =  soapObject.getProperty("imagem").toString();


            String endereco = soapObject.getProperty("endereco").toString().replaceAll("Endereco", "").replaceAll("endereco=", "").replaceAll("latitude=", "").replaceAll("longitude=", "").replace("{", "").replace("}", "");
            String contato = soapObject.getProperty("contato").toString().replaceAll("Contato","").replaceAll("celular=","").replaceAll("email=","").replaceAll("telefone=","").replace("{", "").replace("}", "");

            String endCompleto[] = endereco.trim().split(";");
            String cntCompleto[] = contato.trim().split(";");


            String enderecoCerto = endCompleto[0];
            String latitude = endCompleto[1].replace(" ", "");
            String longitude = endCompleto[2].replace(" ", "");

            String email = cntCompleto[1].replace(" ", "");
            String celular = cntCompleto[0].replace(" ", "");
            String telefone = cntCompleto[2].replace(" ", "");

            Evento evento = new Evento();
            enderecoObj.setEndereco(enderecoCerto);
            enderecoObj.setLatitude(latitude);
            enderecoObj.setLongitude(longitude);
            contatoObj.setTelefone(telefone);
            contatoObj.setCelular(celular);
            contatoObj.setEmail(email);

            evento.setId_evento(id_evento);
            evento.setNome(nome);
            evento.setLocal(local);
            evento.setData_evento(data_evento);
            evento.setValor_ingresso(valor_ingresso);
            evento.setDescricao(descricao);
            evento.setImagem(imagem);
            evento.setEndereco(enderecoObj);
            evento.setContato(contatoObj);

            lista.add(evento);

        }

        return lista;
    }

    public void eventoCadastrar(Evento evento) throws Exception {

        SoapObject soap = new SoapObject(NOMESPACE,"eventoCadastrar");
        SoapObject soapEvento = new SoapObject(NOMESPACE,"evento");

        SoapObject end = new SoapObject(NOMESPACE,"endereco");
        end.addProperty("endereco", evento.getEndereco().getEndereco());
        end.addProperty("latitude", evento.getEndereco().getLatitude());
        end.addProperty("longitude", evento.getEndereco().getLongitude());

        SoapObject cnt = new SoapObject(NOMESPACE, "contato");
        cnt.addProperty("email", evento.getContato().getEmail());
        cnt.addProperty("celular", evento.getContato().getCelular());
        cnt.addProperty("telefone", evento.getContato().getTelefone());

        soapEvento.addSoapObject(end);
        soapEvento.addSoapObject(cnt);

        soapEvento.addProperty("id_evento", evento.getId_evento());
        soapEvento.addProperty("nome", evento.getNome());
        soapEvento.addProperty("local", evento.getLocal());
        soapEvento.addProperty("data_evento", evento.getData_evento());
        soapEvento.addProperty("valor_ingresso", String.valueOf(evento.getValor_ingresso()));
        soapEvento.addProperty("descricao", evento.getDescricao());
        soapEvento.addProperty("imagem", evento.getImagem());
        soapEvento.addProperty("id_user", evento.getId_user());

        soap.addSoapObject(soapEvento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;


        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:eventoCadastrar", envelope);

        SoapObject response = (SoapObject)envelope.getResponse();

    }

    public void eventoAtualizar(Evento evento) throws Exception {
        SoapObject soap = new SoapObject(NOMESPACE,"eventoAtualizar");
        SoapObject soapEvento = new SoapObject(NOMESPACE,"evento");

        SoapObject end = new SoapObject(NOMESPACE,"endereco");
        end.addProperty("endereco", evento.getEndereco().getEndereco());
        end.addProperty("latitude", evento.getEndereco().getLatitude());
        end.addProperty("longitude", evento.getEndereco().getLongitude());

        SoapObject cnt = new SoapObject(NOMESPACE, "contato");
        cnt.addProperty("email", evento.getContato().getEmail());
        cnt.addProperty("celular", evento.getContato().getCelular());
        cnt.addProperty("telefone", evento.getContato().getTelefone());

        soapEvento.addSoapObject(end);
        soapEvento.addSoapObject(cnt);

        soapEvento.addProperty("id_evento", evento.getId_evento());
        soapEvento.addProperty("nome", evento.getNome());
        soapEvento.addProperty("local", evento.getLocal());
        soapEvento.addProperty("data_evento", evento.getData_evento());
        soapEvento.addProperty("valor_ingresso", String.valueOf(evento.getValor_ingresso()));
        soapEvento.addProperty("descricao", evento.getDescricao());
        soapEvento.addProperty("imagem", evento.getImagem());
        soapEvento.addProperty("id_user", evento.getId_user());

        soap.addSoapObject(soapEvento);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        envelope.implicitTypes = true;

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
        httpTransportSE.call("urn:eventoAtualizar", envelope);
    }}
