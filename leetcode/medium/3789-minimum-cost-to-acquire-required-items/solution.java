
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long both = Math.min(need1, need2);

        // Option 1: Buy type 1 and type 2 separately
        long option1 = (long) cost1 * need1
                     + (long) cost2 * need2;

        // Option 2: Use type 3 for common requirements
        long option2 = both * costBoth
                     + (long) (need1 - both) * cost1
                     + (long) (need2 - both) * cost2;

        // Option 3: Buy only type 3 items (may exceed requirements)
        long option3 = (long) Math.max(need1, need2) * costBoth;

        return Math.min(option1, Math.min(option2, option3));
    }
}
