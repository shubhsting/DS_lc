import java.util.*;

public class heap {

    // leetcode 871
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        int count = 0;
        for (int[] st : stations) {
            if (startFuel >= st[0]) {
                pq.add(st[1]);
            } else {
                if (pq.size() == 0)
                    return -1;
                else {
                    while (startFuel < st[0] && pq.size() != 0) {
                        startFuel += pq.remove();
                        System.out.println(startFuel);
                        count++;
                    }
                    pq.add(st[1]);
                    if (startFuel < st[0])
                        return -1;
                }
            }
        }

        while (startFuel < target) {
            if (pq.size() == 0)
                return -1;
            startFuel += pq.remove();
            count++;
        }
        return count;
    }

    // leetcode 128
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        return longestincreasing(nums);
    }

    public int longestincreasing(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i] - 1)) {
                int temp = map.get(arr[i] - 1) + 1;
                map.put(arr[i], temp);

            } else
                map.put(arr[i], 1);

            maxi = Math.max(maxi, map.get(arr[i]));
        }
        return maxi;
    }

    // leetcode 205
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char chs = s.charAt(i);
            char cht = t.charAt(i);
            if (!map.containsKey(chs) && !set.contains(cht)) {
                map.put(chs, cht);
                set.add(cht);
            } else if (!map.containsKey(chs) && set.contains(cht))
                return false;
            else if (map.containsKey(chs) && map.get(chs) != cht)
                return false;
        }
        return true;
    }

    // leetcode 954

    public boolean canReorderDoubled(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(A);
        for (int a : A) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int count = 0;
        for (int a : A) {
            int y = 2 * a;
            if (map.containsKey(a) && map.containsKey(y)) {
                map.put(a, map.get(a) - 1);
                map.put(y, map.get(y) - 1);
                if (map.get(a) == 0) {
                    map.remove(a);
                }
                if (a != y && map.get(y) == 0) {
                    map.remove(y);
                }
                count++;
            }
        }
        return count == A.length / 2;
    }

    // leetcode 954
    public boolean hasGroupsSizeX(int[] deck) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < deck.length; i++)
            map.put(deck[i], map.getOrDefault(deck[i], 0) + 1);
        int fgcd = (int) 1e8;
        for (int a : map.keySet()) {
            if (fgcd == (int) 1e8)
                fgcd = map.get(a);
            else
                fgcd = gcd(fgcd, map.get(a));
        }
        return fgcd >= 2;
    }

    public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }



    

}