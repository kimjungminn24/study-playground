package com.example.demo.algorithm.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Sort {

    /**
     * 1. 버블 정렬 (Bubble Sort)
     * 인접한 두 원소를 비교하여 필요시 교환하는 과정을 반복하는 정렬 알고리즘
     * 시간 복잡도: O(n²) - 최악, 평균, 최선 모두 동일
     * 공간 복잡도: O(1) - 추가 메모리 공간 거의 필요 없음
     * 안정적인 정렬 (동일한 값의 상대적 위치가 유지됨)
     *
     * @param arr 정렬할 배열
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 매 반복마다 가장 큰 원소가 맨 뒤로 이동하므로, 다음 반복에서는 마지막 i개 원소는 이미 정렬됨
            for (int j = 0; j < n - 1 - i; j++) {
                // 현재 원소가 다음 원소보다 크면 두 원소 교환
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 2. 선택 정렬 (Selection Sort)
     * 현재 위치에 올 원소를 찾아 선택하여 교환하는 정렬 알고리즘
     * 시간 복잡도: O(n²) - 최악, 평균, 최선 모두 동일
     * 공간 복잡도: O(1) - 추가 메모리 공간 거의 필요 없음
     * 불안정 정렬 (동일한 값의 상대적 위치가 바뀔 수 있음)
     * 교환 횟수가 버블 정렬보다 적어 성능상 유리할 수 있음
     *
     * @param arr 정렬할 배열
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // i번째 위치에 올 최소값의 인덱스를 찾음
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // 현재 최소값보다 더 작은 값을 찾으면 최소값 인덱스 갱신
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 찾은 최소값을 i번째 위치로 이동 (필요한 경우에만)
            swap(arr, i, minIndex);
        }
    }

    /**
     * 3. 삽입 정렬 (Insertion Sort)
     * 이미 정렬된 부분과 비교하여 자신의 위치를 찾아 삽입하는 정렬 알고리즘
     * 시간 복잡도: O(n²) - 최악, 평균 / O(n) - 최선(이미 정렬된 경우)
     * 공간 복잡도: O(1) - 추가 메모리 공간 거의 필요 없음
     * 안정적인 정렬 (동일한 값의 상대적 위치가 유지됨)
     * 작은 데이터셋이나 거의 정렬된 데이터에 효율적
     *
     * @param arr 정렬할 배열
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 현재 원소를 key로 저장
            int key = arr[i];
            int j = i - 1;

            // key보다 큰 원소들을 오른쪽으로 한 칸씩 이동시킴
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // key를 적절한 위치에 삽입
            arr[j + 1] = key;
        }
    }

    /**
     * 4. 병합 정렬 (Merge Sort)
     * 분할 정복 방식으로 배열을 반으로 나누고, 정렬 후 병합하는 알고리즘
     * 시간 복잡도: O(n log n) - 최악, 평균, 최선 모두 동일
     * 공간 복잡도: O(n) - 임시 배열 필요
     * 안정적인 정렬 (동일한 값의 상대적 위치가 유지됨)
     * 큰 데이터셋에서 효율적이나 추가 메모리 필요
     *
     * @param arr 정렬할 배열
     */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length]; // 한 번만 생성하여 재사용
        mergeSort(arr, temp, 0, arr.length - 1);
    }



    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return; // 베이스 케이스: 원소가 1개 이하일 때

        int mid = left + (right - left) / 2;

        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1); // 원본을 temp로 복사

        int i = left;  // 왼쪽 배열 시작점
        int j = mid + 1; // 오른쪽 배열 시작점
        int k = left;  // 원본 배열에서 삽입할 위치

        // 두 부분을 비교하면서 정렬
        while (i <= mid && j <= right) {
            arr[k++] = (temp[i] <= temp[j]) ? temp[i++] : temp[j++];
        }

        // 왼쪽 배열의 남은 요소 복사 (오른쪽은 이미 제자리에 있음)
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }
    /**
     * 5. 퀵 정렬 (Quick Sort)
     * 분할 정복 방식으로 피벗을 기준으로 작은 값과 큰 값을 분할하는 알고리즘
     * 시간 복잡도: O(n log n) - 평균, 최선 / O(n²) - 최악(이미 정렬된 배열과 같은 특수 케이스)
     * 공간 복잡도: O(log n) - 재귀 호출 스택 공간
     * 불안정 정렬 (동일한 값의 상대적 위치가 바뀔 수 있음)
     * 대부분의 상황에서 가장 빠른 정렬 알고리즘 중 하나
     *
     * @param arr 정렬할 배열
     * @param low 시작 인덱스
     * @param high 끝 인덱스
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 피벗을 기준으로 배열 분할 후 피벗의 최종 위치를 반환
            int pivot = partition(arr, low, high);

            // 피벗의 왼쪽 부분 정렬
            quickSort(arr, low, pivot - 1);
            // 피벗의 오른쪽 부분 정렬
            quickSort(arr, pivot + 1, high);
        }
    }

    /**
     * 퀵 정렬의 분할 과정
     * 피벗보다 작은 원소는 왼쪽, 큰 원소는 오른쪽으로 이동시킴
     *
     * @param arr 작업할 배열
     * @param low 시작 인덱스
     * @param high 끝 인덱스 (피벗으로 사용됨)
     * @return 피벗의 최종 위치 인덱스
     */
    private static int partition(int[] arr, int low, int high) {
        // 배열의 마지막 요소를 피벗으로 선택
        int pivot = arr[high];
        int i = low - 1;  // 피벗보다 작은 요소들의 경계

        // 배열을 순회하며 피벗보다 작은 요소를 왼쪽으로 이동
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                swap(arr, ++i, j);
            }
        }

        // 피벗을 올바른 위치로 이동
        swap(arr, i + 1, high);
        return i + 1;  // 피벗의 최종 위치 반환
    }

    /**
     * 6. 힙 정렬 (Heap Sort)
     * 이진 힙 자료구조를 사용하여 최대 힙을 구성하고 정렬하는 알고리즘
     * 시간 복잡도: O(n log n) - 최악, 평균, 최선 모두 동일
     * 공간 복잡도: O(1) - 추가 메모리 공간 거의 필요 없음
     * 불안정 정렬 (동일한 값의 상대적 위치가 바뀔 수 있음)
     * 정렬에 최적화된 완전 이진 트리를 사용
     *
     * @param arr 정렬할 배열
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 최대 힙 구성 (n/2부터 0까지 거꾸로 이동하며 힙 속성 유지)
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);

        // 최대 힙에서 원소를 하나씩 추출하여 배열 끝부터 채움
        for (int i = n - 1; i > 0; i--) {
            // 현재 루트(최대값)를 배열 끝으로 이동
            swap(arr, 0, i);
            // 루트가 제거된 힙을 다시 최대 힙으로 재구성
            heapify(arr, i, 0);
        }
    }

    /**
     * 힙 속성을 유지하도록 재구성하는 메서드
     * 주어진 노드를 기준으로 자식 노드와 비교하여 최대값이 부모 노드가 되도록 조정
     *
     * @param arr 작업할 배열
     * @param n 고려할 배열의 크기
     * @param i 힙을 재구성할 루트 노드 인덱스
     */
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;            // 현재 노드를 최대값으로 가정
        int left = 2 * i + 1;       // 왼쪽 자식 노드
        int right = 2 * i + 2;      // 오른쪽 자식 노드

        // 왼쪽 자식이 현재 최대값보다 크면 최대값 갱신
        if (left < n && arr[left] > arr[largest]) largest = left;

        // 오른쪽 자식이 현재 최대값보다 크면 최대값 갱신
        if (right < n && arr[right] > arr[largest]) largest = right;

        // 최대값이 현재 노드가 아니라면 교환하고 해당 서브트리를 재귀적으로 힙화
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }


    /**
     * @param arr 작업할 배열
     * @param i 첫 번째 인덱스
     * @param j 두 번째 인덱스
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}