package dynamic;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

public class KingdomDivision {
    private static int N;
    private static int MOD;
    private static int[][] same = new int[N][2];
    private static int[][] diff = new int[N][2];


    public static void solve() {

    }

    public static void dfs(int u, int p, List<List<Integer>> adj) {
        List<Integer> children = new ArrayList<>();

        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, adj);
                children.add(v);
            }
        }

        same[u][0] = 1; // u has 0 and all its children have 1 ( 0-1-1 ... )
        same[u][1] = 1; // u has 1 and all its children have 0 ( 1-0-0 ... )
        for (int v : children) {
            same[u][0] = same[u][0] * diff[v][1] % MOD;
            same[u][1] = same[u][1] * diff[v][0] % MOD;
        }

        diff[u][0] = 1; // u has 0 and its children can have any. except same[v][1] 0-1-0-0 ... (v is u's child)
        diff[u][1] = 1; // u has 1 and its children can have any. except same[v][0] 1-0-0-0 ...
        for (int v : children) {
            diff[u][0] = diff[u][0] * (same[v][0] + diff[v][1] + diff[v][0]) % MOD; // except same[v][1]
            diff[u][1] = diff[u][1] * (same[v][1] + diff[v][1] + diff[v][0]) % MOD; // except same[v][0]
        }

        diff[u][0] = (diff[u][0] - same[u][0] + MOD) % MOD;
        diff[u][1] = (diff[u][1] - same[u][1] + MOD) % MOD;
    }
}