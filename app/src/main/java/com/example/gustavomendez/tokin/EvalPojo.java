package com.example.gustavomendez.tokin;

public class EvalPojo {
    private String nameBand, nameRes, userIdEvaluador, userIdEvaluado, userIdBand, userIdRes, idEvent, comentario;
    private float rating;

    public EvalPojo() {
    }

    public EvalPojo(String nameBand, String nameRes, String userIdEvaluador, String userIdEvaluado, String userIdBand, String userIdRes,
                    String idEvent, String comentario, float rating) {
        this.nameBand = nameBand;
        this.nameRes = nameRes;
        this.userIdEvaluador = userIdEvaluador;
        this.userIdEvaluado = userIdEvaluado;
        this.rating = rating;
        this.userIdRes = userIdRes;
        this.userIdBand = userIdBand;
        this.comentario = comentario;
        this.idEvent = idEvent;


    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameBand() {
        return nameBand;
    }

    public void setNameBand(String nameBand) {
        this.nameBand = nameBand;
    }

    public String getNameRes() {
        return nameRes;
    }

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    public String getUserIdEvaluador() {
        return userIdEvaluador;
    }

    public void setUserIdEvaluador(String userIdEvaluador) {
        this.userIdEvaluador = userIdEvaluador;
    }

    public String getUserIdEvaluado() {
        return userIdEvaluado;
    }

    public void setUserIdEvaluado(String userIdEvaluado) {
        this.userIdEvaluado = userIdEvaluado;
    }

    public String getUserIdBand() {
        return userIdBand;
    }

    public void setUserIdBand(String userIdBand) {
        this.userIdBand = userIdBand;
    }

    public String getUserIdRes() {
        return userIdRes;
    }

    public void setUserIdRes(String userIdRes) {
        this.userIdRes = userIdRes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
