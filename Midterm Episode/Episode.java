public class Episode {
    private String showTitle;
    private int seasonNumber;
    private int episodeNumber;

    public  Episode(String title, int season, int episode) {
        showTitle = title;
        seasonNumber = season;
        episodeNumber = episode;
    }
    public  Episode(String title) {
        showTitle = title;
        seasonNumber = 1;
        episodeNumber = 1;
    }
    public  Episode() {
        showTitle = "";
        seasonNumber = 1;
        episodeNumber = 1;
    }
    public String getTitle() {
        return showTitle;
    }
    public void setTitle(String title) {
        showTitle = title;
    }
    public boolean equals(Episode comp) {
        return ( (this.showTitle == comp.showTitle) && (this.seasonNumber == comp.seasonNumber) && (this.episodeNumber == comp.episodeNumber));
    }
    public boolean comesBefore(Episode comp) {
        if (this.showTitle == comp.showTitle) {
            if (this.seasonNumber < comp.seasonNumber) {
                return true;
            } else if ((this.seasonNumber == comp.seasonNumber) && (this.episodeNumber < comp.episodeNumber)) {
                    return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public void printEpisode() {
        System.out.println(this.showTitle + ": S:"+ this.seasonNumber+", E:"+this.episodeNumber);
    }
}