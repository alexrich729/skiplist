# skiplist
Generic skiplist implementation, provides O(logn) operations without problems of rebalancing a tree.

example usage:
        SkipList sl = new SkipList();
        sl.insert(13);
        System.out.println(sl);
        //prints: 13,
        sl.insert(24);
        System.out.println(sl.toStringFull());
        /*print (one example, depends on probability which nodes are bumped up):
         * Node{item=24, right=null, left=null, up=null, down=24, height=3}  
         * Node{item=24, right=null, left=null, up=24, down=24, height=2}  
         * Node{item=13, right=24, left=null, up=null, down=null, height=1}  Node{item=24, right=null, left=13, up=24, down=null, height=1} 
         */
        System.out.println(sl);
        //prints: 13,24,
        sl.insert(24);
        sl.delete(24);
        sl.delete(1000);
        System.out.println(sl);
        //prints: 13,
