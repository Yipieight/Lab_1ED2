package org.example;

public class BTree {
    private int t;  // Grado mínimo del árbol B
    private Node root;

    // Clase Node para representar los nodos del árbol B
    private class Node {
        int n;
        Book[] keys;
        Node[] children;
        boolean isLeaf;

        Node(boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.keys = new Book[2 * t - 1];
            this.children = new Node[2 * t];
            this.n = 0;
        }

        void insertNonFull(Book book) {
            int i = n - 1;
            if (isLeaf) {
                while (i >= 0 && book.getIsbn().compareTo(keys[i].getIsbn()) < 0) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = book;
                n++;
            } else {
                while (i >= 0 && book.getIsbn().compareTo(keys[i].getIsbn()) < 0) {
                    i--;
                }
                if (children[i + 1].n == 2 * t - 1) {
                    splitChild(i + 1, children[i + 1]);
                    if (book.getIsbn().compareTo(keys[i + 1].getIsbn()) > 0) {
                        i++;
                    }
                }
                children[i + 1].insertNonFull(book);
            }
        }

        void splitChild(int i, Node y) {
            Node z = new Node(y.isLeaf);
            z.n = t - 1;
            for (int j = 0; j < t - 1; j++) {
                z.keys[j] = y.keys[j + t];
            }
            if (!y.isLeaf) {
                for (int j = 0; j < t; j++) {
                    z.children[j] = y.children[j + t];
                }
            }
            y.n = t - 1;
            for (int j = n; j >= i + 1; j--) {
                children[j + 1] = children[j];
            }
            children[i + 1] = z;
            for (int j = n - 1; j >= i; j--) {
                keys[j + 1] = keys[j];
            }
            keys[i] = y.keys[t - 1];
            n++;
        }

        void remove(String isbn) {
            int idx = findKey(isbn);
            if (idx < n && keys[idx].getIsbn().equals(isbn)) {
                if (isLeaf) {
                    removeFromLeaf(idx);
                } else {
                    removeFromNonLeaf(idx);
                }
            } else {
                if (isLeaf) {
                    return;
                }
                boolean flag = (idx == n);
                if (children[idx].n < t) {
                    fill(idx);
                }
                if (flag && idx > n) {
                    children[idx - 1].remove(isbn);
                } else {
                    children[idx].remove(isbn);
                }
            }
        }

        int findKey(String isbn) {
            int idx = 0;
            while (idx < n && keys[idx].getIsbn().compareTo(isbn) < 0) {
                idx++;
            }
            return idx;
        }

        void removeFromLeaf(int idx) {
            for (int i = idx + 1; i < n; ++i) {
                keys[i - 1] = keys[i];
            }
            n--;
        }

        void removeFromNonLeaf(int idx) {
            Book k = keys[idx];
            if (children[idx].n >= t) {
                Book pred = getPred(idx);
                keys[idx] = pred;
                children[idx].remove(pred.getIsbn());
            } else if (children[idx + 1].n >= t) {
                Book succ = getSucc(idx);
                keys[idx] = succ;
                children[idx + 1].remove(succ.getIsbn());
            } else {
                merge(idx);
                children[idx].remove(k.getIsbn());
            }
        }

        Book getPred(int idx) {
            Node cur = children[idx];
            while (!cur.isLeaf) {
                cur = cur.children[cur.n];
            }
            return cur.keys[cur.n - 1];
        }

        Book getSucc(int idx) {
            Node cur = children[idx + 1];
            while (!cur.isLeaf) {
                cur = cur.children[0];
            }
            return cur.keys[0];
        }

        void fill(int idx) {
            if (idx != 0 && children[idx - 1].n >= t) {
                borrowFromPrev(idx);
            } else if (idx != n && children[idx + 1].n >= t) {
                borrowFromNext(idx);
            } else {
                if (idx != n) {
                    merge(idx);
                } else {
                    merge(idx - 1);
                }
            }
        }

        void borrowFromPrev(int idx) {
            Node child = children[idx];
            Node sibling = children[idx - 1];
            for (int i = child.n - 1; i >= 0; --i) {
                child.keys[i + 1] = child.keys[i];
            }
            if (!child.isLeaf) {
                for (int i = child.n; i >= 0; --i) {
                    child.children[i + 1] = child.children[i];
                }
            }
            child.keys[0] = keys[idx - 1];
            if (!child.isLeaf) {
                child.children[0] = sibling.children[sibling.n];
            }
            keys[idx - 1] = sibling.keys[sibling.n - 1];
            child.n += 1;
            sibling.n -= 1;
        }

        void borrowFromNext(int idx) {
            Node child = children[idx];
            Node sibling = children[idx + 1];
            child.keys[child.n] = keys[idx];
            if (!child.isLeaf) {
                child.children[child.n + 1] = sibling.children[0];
            }
            keys[idx] = sibling.keys[0];
            for (int i = 1; i < sibling.n; ++i) {
                sibling.keys[i - 1] = sibling.keys[i];
            }
            if (!sibling.isLeaf) {
                for (int i = 1; i <= sibling.n; ++i) {
                    sibling.children[i - 1] = sibling.children[i];
                }
            }
            child.n += 1;
            sibling.n -= 1;
        }

        void merge(int idx) {
            Node child = children[idx];
            Node sibling = children[idx + 1];
            child.keys[t - 1] = keys[idx];
            for (int i = 0; i < sibling.n; ++i) {
                child.keys[i + t] = sibling.keys[i];
            }
            if (!child.isLeaf) {
                for (int i = 0; i <= sibling.n; ++i) {
                    child.children[i + t] = sibling.children[i];
                }
            }
            for (int i = idx + 1; i < n; ++i) {
                keys[i - 1] = keys[i];
            }
            for (int i = idx + 2; i <= n; ++i) {
                children[i - 1] = children[i];
            }
            child.n += sibling.n + 1;
            n--;
        }
    }

    public BTree(int t) {
        this.t = t;
        this.root = new Node(true);
    }

    public void insert(Book book) {
        Node r = root;
        if (r.n == 2 * t - 1) {
            Node s = new Node(false);
            s.children[0] = r;
            r.splitChild(0, r);
            s.insertNonFull(book);
            root = s;
        } else {
            r.insertNonFull(book);
        }
    }

    public Book search(Node x, String isbn) {
        int i = 0;
        while (i < x.n && isbn.compareTo(x.keys[i].getIsbn()) > 0) {
            i++;
        }
        if (i < x.n && isbn.equals(x.keys[i].getIsbn())) {
            return x.keys[i];
        } else if (x.isLeaf) {
            return null;
        } else {
            return search(x.children[i], isbn);
        }
    }

    public Book search(String isbn) {
        return search(root, isbn);
    }

    public Book searchByName(String name) {
        return searchByName(root, name);
    }

    private Book searchByName(Node node, String name) {
        int i = 0;
        while (i < node.n && name.compareTo(node.keys[i].getName()) > 0) {
            i++;
        }
        if (i < node.n && name.equals(node.keys[i].getName())) {
            return node.keys[i];
        }
        if (node.isLeaf) {
            return null;
        }
        return searchByName(node.children[i], name);
    }


    public void delete(String isbn) {
        if (root == null) {
            return;
        }
        root.remove(isbn);
        if (root.n == 0) {
            if (root.isLeaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
    }

    public void update(String isbn, String field, String newValue) {
        Book book = search(isbn);
        if (book != null) {
            switch (field) {
                case "name":
                    book.setName(newValue);
                    break;
                case "author":
                    book.setAuthor(newValue);
                    break;
                case "category":
                    book.setCategory(newValue);
                    break;
                case "price":
                    book.setPrice(Double.parseDouble(newValue));
                    break;
                case "quantity":
                    book.setQuantity(Integer.parseInt(newValue));
                    break;
            }
        }
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("└─ ");
                indent += "   ";
            } else {
                System.out.print("├─ ");
                indent += "|  ";
            }

            System.out.print("[");
            for (int i = 0; i < node.n; i++) {
                System.out.print(node.keys[i].getIsbn());
                if (i < node.n - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            for (int i = 0; i <= node.n; i++) {
                printTree(node.children[i], indent, i == node.n);
            }
        }
    }
}
