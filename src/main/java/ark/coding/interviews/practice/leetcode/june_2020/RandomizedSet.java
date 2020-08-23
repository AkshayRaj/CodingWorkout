/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode.june_2020;

import java.util.*;

class RandomizedSet {

    List<Integer> list;
    Map<Integer, Integer> valIndexMap;
    Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        valIndexMap = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!valIndexMap.containsKey(val)) {
            list.add(val);
            valIndexMap.put(val, list.size() - 1);
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (valIndexMap.containsKey(val)) {
            int indexOfElementToBeRemoved = valIndexMap.get(val);
            int lastElementIndex = list.size()-1;

            // put last element in index of element to remove
            int lastElement = list.get(lastElementIndex);
            list.set(indexOfElementToBeRemoved, lastElement);
            list.remove(lastElementIndex);

            valIndexMap.replace(lastElement, indexOfElementToBeRemoved);
            valIndexMap.remove(val);
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if (list.isEmpty()) {
            throw new RuntimeException("Cannot retrieve random number when list is empty");
        }
        int randomIndex = Math.abs(random.nextInt()) % list.size();
        return list.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */