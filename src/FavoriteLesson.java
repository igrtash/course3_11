public enum FavoriteLesson {
    Mathematics(1),
    Literature(2),
    RussianLanguage(3),
    History(4),
    Biology(5),
    Chemistry(6),
    Geography(7),
    Music(8),
    PhysicalEducation(9);

    private int num;

    public int getNum() {
        return num;
    }

    FavoriteLesson(int num) {
        this.num = num;
    }
}
