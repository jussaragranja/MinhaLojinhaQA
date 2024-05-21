package util;

public class Constants {

    //PATHS
    public static final String PATH_USERS = "usuarios";
    public static final String PATH_LOGIN = "login";
    public static final String PATH_PRODUTOS = "produtos";
    public static final String PATH_CARRINHOS = "carrinhos";
    public static final String PATH_CARRINHOS_CANCELAR_COMPRA = "carrinhos/cancelar-compra";

    //PARAMETERS

    public static final String PARAM_ID = "_id";
    public static final String PARAM_NOME = "nome";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_ADMINISTRADOR = "administrador";
    public static final String PARAM_MESSAGE = "message";
    public static final String PARAM_QUANTIDADE = "quantidade";
    public static final String PARAM_USUARIOS_NOME = "usuarios[0].nome";
    public static final String PARAM_USUARIOS_EMAIL = "usuarios[0].email";
    public static final String PARAM_USUARIOS_PASSWORD = "usuarios[0].password";
    public static final String PARAM_USUARIOS_ADMINISTRADOR = "usuarios[0].administrador";

    public static final String PARAM_PRODUTOS_NOME = "produtos[0].nome";
    public static final String PARAM_PRODUTOS_PRECO = "produtos[0].preco";
    public static final String PARAM_PRODUTOS_DESCRICAO = "produtos[0].descricao";
    public static final String PARAM_PRODUTOS_QUANTIDADE = "produtos[0].quantidade";


    //messages

    public static final String MESSAGE_EMAIL_DEVE_SER_UMA_STRING = "email deve ser uma string";
    public static final String MESSAGE_EMAIL_DEVE_SER_UM_EMAIL_VALIDO = "email deve ser um email válido";
    public static final String MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE = "administrador deve ser 'true' ou 'false'";
    public static final String MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso";
    public static final String MESSAGE_NOME_EH_OBRIGATORIO = "nome é obrigatório";
    public static final String MESSAGE_PASSWORD_EH_OBRIGATORIO = "password é obrigatório";
    public static final String MESSAGE_EMAIL_EH_OBRIGATORIO = "email é obrigatório";
    public static final String MESSAGE_ADMINISTRADOR_EH_OBRIGATORIO = "administrador é obrigatório";
    public static final String MESSAGE_NOME_NAO_PODE_FICAR_EM_BRANCO = "nome não pode ficar em branco";
    public static final String MESSAGE_EMAIL_NAO_PODE_FICAR_EM_BRANCO = "email não pode ficar em branco";
    public static final String MESSAGE_PASSWORD_NAO_PODE_FICAR_EM_BRANCO = "password não pode ficar em branco";

    public static final String MESSAGE_PASSWORD_DEVE_SER_UMA_STRING = "password deve ser uma string";
    public static final String MESSAGE_NOME_DEVE_SER_UMA_STRING = "nome deve ser uma string";
    public static final String MESSAGE_EMAIL_E_OU_SENHA_INVALIDOS = "Email e/ou senha inválidos";
    public static final String MESSAGE_ESTE_EMAIL_JA_ESTA_SENDO_USADO = "Este email já está sendo usado";
    public static final String MESSAGE_LOGIN_REALIZADO_COM_SUCESSO = "Login realizado com sucesso";
    public static final String MESSAGE_REGISTRO_EXCLUIDO_COM_SUCESSO_ESTOQUE_DOS_PRODUTOS_REABASTECIDO = "Registro excluído com sucesso. Estoque dos produtos reabastecido";

    //CONFIG

    public static final String BEARER_TOKEN = "Bearer ";
    public static final String AUTHORIZATION_TOKEN = "Authorization";

}
