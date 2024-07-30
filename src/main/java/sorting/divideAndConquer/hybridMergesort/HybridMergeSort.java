package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {

		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		
		if (leftIndex < 0 || rightIndex >= array.length) {
			return;
		}
		
		if (rightIndex - leftIndex + 1 <= SIZE_LIMIT) {
			insertionSort(array, leftIndex, rightIndex);
		} else {

			if (leftIndex >= rightIndex) {
				return;
			}

			int middleIndex = (leftIndex + rightIndex) /2;

			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex + 1, rightIndex);
			merge(array, leftIndex, middleIndex, rightIndex);

		}


	}

	public void insertionSort(T[] array, int leftIndex, int rightIndex) {

		for (int i = leftIndex + 1; i <= rightIndex; i++) {

			T aux = array[i];
			int j = i - 1;

			while ((j >= leftIndex) && aux.compareTo(array[j]) < 0) {
				array[j + 1] = array[j];
				j--;
			}

			array[j + 1] = aux;
		}

		INSERTIONSORT_APPLICATIONS++;
	}

	public void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {

		T[] helper = (T[]) new Comparable[array.length];

		for (int i = 0; i < array.length; i++) {
			helper[i] = array[i];
		}

		int i = leftIndex;
		int j = middleIndex + 1;

		int k = leftIndex;

		while ((i <= middleIndex) && (j <= rightIndex)) {

			if (helper[i].compareTo(helper[j]) <= 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}

			k++;
		}

		while (i <= middleIndex) {
			array[k] = helper[i];
			i++;
			k++;
		}

		while (j <= rightIndex) {
			array[k] = helper[j];
			j++;
			k++;
		}

		MERGESORT_APPLICATIONS++;

	}
}
