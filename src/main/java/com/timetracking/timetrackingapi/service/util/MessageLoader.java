package com.timetracking.timetrackingapi.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static java.util.Objects.nonNull;

public class MessageLoader {

    private static final String MSG_ERRO_INTERNO_REQUISICAO = "msg.erro.interno.requisicao";
    private static final String MSG_ERRO_NAO_ENCONTRADO = "msg.erro.interno.nao.encontrado";

    private static Logger logger = LoggerFactory.getLogger(MessageLoader.class);
    private static final ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("mensagens", new Locale("pt", "BR"));
    }

    public static String getMessage(final String message, final Object... values) {
        String msgErro = getMessage(message);
        return nonNull(values) ? processarValores(msgErro, values) : getMessage(MSG_ERRO_INTERNO_REQUISICAO);
    }

    public static String getMessage(final String message) {
        try {
            final String msg = nonNull(message) ? message : MSG_ERRO_INTERNO_REQUISICAO;
            return bundle.getString(msg);
        } catch (MissingResourceException e) {
            logger.error(e.getMessage(), e);
        }

        return bundle.getString(MSG_ERRO_INTERNO_REQUISICAO);
    }

    private static String processarValores(String msgErro, Object[] values) {
        for (int pos = 0; pos < values.length; pos++) {
            String target = "{" + pos + "}";
            msgErro = msgErro.replace(target, getValue(values[pos]));
        }

        return msgErro;
    }

    private static String getValue(Object value) {
        return nonNull(value) ? (value).toString() : "null";
    }

    public static String getMsgNaoEncontrado(final String iniMsg) {
        return getMessage(MSG_ERRO_NAO_ENCONTRADO, iniMsg);
    }

    public static NoResultException mensagemNaoEncontrado(final String message) {
        return new NoResultException(getMsgNaoEncontrado(message));
    }

}
