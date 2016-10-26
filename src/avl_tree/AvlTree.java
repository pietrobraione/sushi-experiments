package avl_tree;

import jbse.meta.Analysis;

/**
 * Implements an AVL tree. Note that all "matching" is based on the compareTo
 * method.
 * 
 * @author Mark Allen Weiss
 */
/**
 * @SpecField nodes : set AvlNode from this.root | this.nodes = this.root.*(left @+ right) @- null ;
 *
 */
/**
 * @Invariant all x: AvlNode | x in this.root.*(left @+ right) @- null => 
 * (
 *		(x !in x.^(left @+ right)) && 
 *		(all y: AvlNode | (y in x.left.*(left @+ right) @-null) => y.element < x.element ) && 
 *		(all y: AvlNode | (y in x.right.*(left @+right) @- null) => x.element < y.element ) && 
 *		(x.left=null && x.right=null => x.height=0) && 
 *		(x.left=null && x.right!=null => x.height=1 && x.right.height=0) && 
 *		(x.left!=null && x.right=null => x.height=1 && x.left.height=0) && 
 *		(x.left!=null && x.right!=null => x.height= (x.left.height>x.right.height ? x.left.height : x.right.height )+1 && ( (x.left.height > x.right.height ? x.left.height - x.right.height : x.right.height - x.left.height ))<=1)
 * );
 * 
 */
public class AvlTree {
	/**
	 * Double rotate binary tree node: first left child with its right child; then
	 * node k3 with new left child. For AVL trees, this is a double rotation for
	 * case 2. Update heights, then return new root.
	 */
	private static AvlNode doubleWithLeftChild(final AvlNode k3) {
		k3.left = AvlTree.rotateWithRightChild(k3.left);
		return AvlTree.rotateWithLeftChild(k3);
	}

	/**
	 * Double rotate binary tree node: first right child with its left child; then
	 * node k1 with new right child. For AVL trees, this is a double rotation for
	 * case 3. Update heights, then return new root.
	 */
	private static AvlNode doubleWithRightChild(final AvlNode k1) {
		k1.right = AvlTree.rotateWithLeftChild(k1.right);
		return AvlTree.rotateWithRightChild(k1);
	}

	/**
	 * Return the height of node t, or -1, if null.
	 */
	private static int height(final AvlNode t) {
		return t == null ? -1 : t.height;
	}

	/**
	 * Return maximum of lhs and rhs.
	 */
	private static int max(final int lhs, final int rhs) {
		return lhs > rhs ? lhs : rhs;
	}

	/**
	 * Rotate binary tree node with left child. For AVL trees, this is a single
	 * rotation for case 1. Update heights, then return new root.
	 */
	private static AvlNode rotateWithLeftChild(final AvlNode k2) {
		final AvlNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
//INSTRUMENTATION BEGIN
		if (k2.left != null) k2.left.parent = k2;
		k1.parent = k2.parent;
		k2.parent = k1;
//INSTRUMENTATION END
		k2.height = AvlTree.max(AvlTree.height(k2.left), AvlTree
				.height(k2.right)) + 1;
		k1.height = AvlTree.max(AvlTree.height(k1.left), k2.height) + 1;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child. For AVL trees, this is a single
	 * rotation for case 4. Update heights, then return new root.
	 */
	private static AvlNode rotateWithRightChild(final AvlNode k1) {
		final AvlNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
//INSTRUMENTATION BEGIN
		if (k1.right != null) k1.right.parent = k1;
		k2.parent = k1.parent;
		k1.parent = k2;
//INSTRUMENTATION END
		k1.height = AvlTree.max(AvlTree.height(k1.left), AvlTree
				.height(k1.right)) + 1;
		k2.height = AvlTree.max(AvlTree.height(k2.right), k1.height) + 1;
		return k2;
	}

	/** The tree root. */
	private/*@ nullable @*/AvlNode root;

	/**
	 * Construct the tree.
	 */
	public AvlTree() {
		this.root = null;
	}

	/**
	 * Internal method to get element field.
	 * 
	 * @param t
	 *          the node.
	 * @return the element field or null if t is null.
	 */
	private int elementAt(final AvlNode t) {
		return t == null ? -1 : t.element;
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *          the item to search for.
	 * @return the matching item or null if not found.
	 */
	// requires this.ordered() && this.wellFormed() && this.balanced();
	// ensures this.ordered() && this.wellFormed() && this.balanced();
	public int find(final int x) {
		return elementAt(find(x, this.root));
	}

	/**
	 * @Modifies_Everything
	 * 
	 * @Ensures (some n: AvlNode | n in this.nodes && n.element = x)
	 *		? return in  { n : AvlNode | n in this.nodes && n.element = x } 
	 *		: return = null ;
	 */
//INSTRUMENTATION BEGIN
	//public
	private
//INSTRUMENTATION END
	/*@ nullable @*/AvlNode findNode(final int x) {
		return find(x, this.root);
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *          is item to search for.
	 * @param t
	 *          the node that roots the tree.
	 * @return node containing the matched item.
	 */
	private AvlNode find(final int x, final AvlNode arg) {
		AvlNode t = arg;
		while (t != null) {
			if (x < t.element) {
				t = t.left;
			} else if (x > t.element) {
				t = t.right;
			} else {
				return t; // Match
			}
		}

		return null; // No match
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	//requires this.ordered() && this.wellFormed() && this.balanced();
	//ensures this.ordered() && this.wellFormed() && this.balanced() && this.maxElement(\result);
	public int findMax() {
		return elementAt(findMax(this.root));
	}

	/**
	 * @Modifies_Everything
	 * 
	 * @Returns some this.nodes 
	 *		? {n in this.nodes | no y in AvlNode | ( y in this.nodes && y.element > n.element ) }
	 *		: null ;
	 */
//INSTRUMENTATION BEGIN
	//public
	private
//INSTRUMENTATION END
	/*@ nullable @*/AvlNode fmax() {
		return findMax(this.root);
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *          the node that roots the tree.
	 * @return node containing the largest item.
	 */
	private AvlNode findMax(final AvlNode arg) {
		AvlNode t = arg;
		if (t == null) {
			return t;
		}

		while (t.right != null) {
			t = t.right;
		}
		return t;
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	//requires this.ordered() && this.wellFormed() && this.balanced();
	//ensures this.ordered() && this.wellFormed() && this.balanced() && this.minElement(\result);
	public int findMin() {
		return elementAt(findMin(this.root));
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *          the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private AvlNode findMin(AvlNode t) {
		if (t == null) {
			return t;
		}

		while (t.left != null) {
			t = t.left;
		}
		return t;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *          the item to insert.
	 */
	// requires ordered() && wellFormed() && balanced();
	// ensures balanced() && ordered();
	/**
	 * @Modifies_Everything 
	 * 
	 * @Ensures some n: AvlNode | ( n in this.nodes  && n.element == x ) ;
	 */
	public void insertElem(final int x) {
		this.root = insert(x, this.root);
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *          the item to insert.
	 * @param t
	 *          the node that roots the tree.
	 * @return the new root.
	 */
	private AvlNode insert(final int x, final AvlNode arg) {
		AvlNode t = arg;
		if (t == null) {
			t = new AvlNode(x, null, null, null);
		} else if (x < t.element) {
			t.left = insert(x, t.left);
//INSTRUMENTATION BEGIN
			t.left.parent = t;
//INSTRUMENTATION END
			if (AvlTree.height(t.left) - AvlTree.height(t.right) == 2) {
				if (x < t.left.element) {
					t = AvlTree.rotateWithLeftChild(t);
				} else {
					t = AvlTree.doubleWithLeftChild(t);
				}
			}
		} else if (x > t.element) {
			t.right = insert(x, t.right);
//INSTRUMENTATION BEGIN
			t.right.parent = t;
//INSTRUMENTATION END
			if (AvlTree.height(t.right) - AvlTree.height(t.left) == 2) {
				if (x > t.right.element) {
					t = AvlTree.rotateWithRightChild(t);
				} else {
					t = AvlTree.doubleWithRightChild(t);
				}
			}
		} else {
			; // Duplicate; do nothing
		}
		t.height = AvlTree.max(AvlTree.height(t.left), AvlTree.height(t.right)) + 1;
		return t;
	}


	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.root == null;
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		this.root = null;
	}


	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *          the item to remove.
	 */
//INSTRUMENTATION BEGIN
	/*
	public void remove(final int x) {
		// System.out.println( "Sorry, remove unimplemented" );
	}*/
//INSTRUMENTATION END

}
