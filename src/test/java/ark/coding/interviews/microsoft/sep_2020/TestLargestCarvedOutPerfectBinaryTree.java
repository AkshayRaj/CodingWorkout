package ark.coding.interviews.microsoft.sep_2020;

import ark.coding.interviews.microsoft.sep_2020.helper.Tree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLargestCarvedOutPerfectBinaryTree {
    private LargestCarvedOutPerfectBinaryTree runner;
    Tree t1;
    Tree t1l, t2;
    Tree t1r, t3;
    Tree t2l, t4;
    Tree t3r, t5;
    Tree t3l, t6;
    Tree t5l;
    Tree t5r;
    Tree t6l;
    Tree t6r;

    @Before
    public void setup() {
        runner = new LargestCarvedOutPerfectBinaryTree();
    }

    @Test
    public void testSize_MaxPerfectSingleNode() {
        t1 = new Tree();

        assertEquals(1, runner.solution(t1));
    }

    @Test
    public void testSize_MaxPerfectAtRoot() {
        t1 = new Tree();
        t1.l = new Tree();

        assertEquals(1, runner.solution(t1));
    }

    @Test
    public void testSize_MaxPerfectAtRoot_ChildrenEqualSizes() {
        t1 = new Tree();
        t1.l = new Tree();
        t1.r = new Tree();

        assertEquals(3, runner.solution(t1));
    }

    @Test
    public void testSize_MaxPerfectAtRoot_ChildrenUnEqualSizes() {
        t1 = new Tree();
        t1.l = new Tree();
        t1.r = new Tree();
        t1.l.l = new Tree();

        assertEquals(3, runner.solution(t1));
    }

    @Test
    public void testSize_MaxPerfectAtRoot_ImperfectChildren() {
        t1 = new Tree();
        t1.l = new Tree();
        t1.r = new Tree();
        t1.l.l = new Tree();
        t1.r.r = new Tree();

        assertEquals(3, runner.solution(t1));
    }

    @Test
    public void testSize_MaxPerfectAtGrandchildrenSubtree() throws Exception {
        t1 = new Tree();
        t1l = new Tree(); t1.l = t1l; Tree t2 = t1l;
        t1r = new Tree(); t1.r = t1r; Tree t3 = t1r;
        t2l = new Tree(); t2.l = t2l; Tree t4 = t2l;
        t3r = new Tree(); t3.r = t3r; Tree t5 = t3r;
        t3l = new Tree(); t3.l = t3l; Tree t6 = t3l;
        t5l = new Tree(); t5.l = t5l;
        t5r = new Tree(); t5.r = t5r;
        t6l = new Tree(); t6.l = t6l;
        t6r = new Tree(); t6.r = t6r;
        t6r.r = new Tree();

        int actual = runner.solution(this.t1);
        int expected = 7;

        assertEquals(expected, actual);
    }
}
