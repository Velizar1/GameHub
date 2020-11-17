package gamehub.demo.model.entity;

public enum GameName {
    LEAGUEOFLEGENDS("https://www.dm.jor.br/wp-content/uploads/2020/08/apps1192490071992666632465ba06e26-359d-445f-8346-227218be9a67.jpg"),
    APEX("https://static.bhphotovideo.com/explora/sites/default/files/styles/top_shot/public/ts-apex-legends-free-to-play-battle-royale_0.jpg?itok=mISX6TSE"),
    HEARTHSTONE("https://mat.bg/wp-content/uploads/2020/03/hs.jpg"),
    OVERWATCH("https://s1.gaming-cdn.com/images/products/2208/orig/overwatch-cover.jpg"),
    VALORANT("https://o.aolcdn.com/images/dims?quality=95&image_uri=https%3A%2F%2Fs.yimg.com%2Fos%2Fcreatr-uploaded-images%2F2020-04%2F3a994ce0-8a0b-11ea-bfbe-014be42d9fb7&client=amp-blogside-v2&signature=ecd6cbc5a90fa5c6ecf9b667d6b6e346127a82e7");
    private final String image;

    GameName(String url) {
        this.image = url;
    }

    public String getImage() {
        return image;
    }
}
