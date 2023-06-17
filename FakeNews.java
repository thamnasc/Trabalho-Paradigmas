public abstract class FakeNews extends EntidadeMovel {

    public FakeNews (String label)
    {
        super(label);
    } 
    @Override
    public String toString()
    {
        return String.format(Cores.ANSI_RED + " " + this.label + " " + Cores.ANSI_RESET);
    }
} 