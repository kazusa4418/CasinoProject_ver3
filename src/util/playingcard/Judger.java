package util.playingcard;

import java.util.List;

@FunctionalInterface
public interface Judger<E extends List<PlayingCard>> {
    public int judge(E e);
}
