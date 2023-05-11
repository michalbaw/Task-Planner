package app.taskplanner.view;

public enum Colors {

    LAZURE("rgb(201, 236, 248)", " linear-gradient(rgb(214, 241, 250), rgb(185, 220, 244))"),
    PINK("rgb(241, 195, 241)", " linear-gradient(rgb(245, 208, 245), rgb(235, 175, 235))"),
    VIOLET("rgb(210, 207, 240)", " linear-gradient(rgb(225, 212, 250), rgb(202, 190, 255))"),
    WHITE("rgb(244, 245, 244)", " linear-gradient(rgb(252, 253, 251), rgb(233, 237, 235))");
    private String bodyColor;
    private String secondColor;
    private Colors(String bodyColor, String secondColor)
    {
        this.bodyColor = bodyColor;
        this.secondColor = secondColor;
    }

    public String getBodyColor() {
        return bodyColor;
    }
    public void setBodyColor(String bodyColor)
    {
        this.bodyColor = bodyColor;
    }
    public void setSecondColor(String secondColor)
    {
        this.secondColor = secondColor;
    }
    public String getSecondColor() {
        return secondColor;
    }
}