public class mergesort {
    public static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] arf = new int[n1];
        int[] ars = new int[n2];

        for (int i = 0; i < n1; i++)
            arf[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            ars[j] = arr[m + 1 + j];

        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (arf[i] < ars[j]) {
                arr[k] = arf[i];
                i++;
            } else {
                arr[k] = ars[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = arf[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = ars[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int arr[], int l, int r) {
        if (l < r) {

            int m = l + (r - l) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

}