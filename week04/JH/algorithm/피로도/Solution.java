class Solution {

    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        return dfs(k, dungeons, visited);
    }

    private int dfs(int fatigue, int[][] dungeons, boolean[] visited) {
        int maxCount = 0;

        for (int i = 0; i < dungeons.length; i++) {
            int requiredFatigue = dungeons[i][0];
            int consumedFatigue = dungeons[i][1];

            if (visited[i] || fatigue < requiredFatigue) {
                continue;
            }

            visited[i] = true;

            int count = 1 + dfs(
                fatigue - consumedFatigue,
                dungeons,
                visited
            );

            maxCount = Math.max(maxCount, count);

            visited[i] = false;
        }

        return maxCount;
    }
}
