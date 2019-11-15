package ConCord.Models;

public class JsonResponse {
    private String fio;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "{" +
                "\"fio\":\"" + fio +
                "\"}";
    }
}
