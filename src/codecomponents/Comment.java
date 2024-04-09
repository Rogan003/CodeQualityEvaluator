package codecomponents;

public class Comment extends CodeComponent {
    private String content;

    public Comment (String content) {
        super();
        this.content = content;
    }

    public Comment (String name, String content) {
        super(name);
        this.content = content;
    }

    @Override
    public String toString() {
        return STR."Comment: \{this.content}\n";
    }
}
