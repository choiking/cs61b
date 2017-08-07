/**
 * Created by Administrator on 2017/5/29.
 */
public class Vertex {
    protected Vertex parent;
    protected int depth;
    protected boolean visited;
    public    int  index;


    public void visit(Vertex origin) {
        this.parent=origin;
        if(origin==null) this.depth=0;
        else this.depth=origin.depth+1;
    }
}
