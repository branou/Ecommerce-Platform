import {createFeatureSelector, createSelector} from '@ngrx/store';
import {ProductState} from './product.reducer';
export const selectProductState = createFeatureSelector<ProductState>('products')
export const selectAllProducts = createSelector(selectProductState,
  (state: ProductState) => state.response?.content || [])
export const selectProductLoading = createSelector(selectProductState,
  (state: ProductState) => state.loading)
export const selectProductError = createSelector(selectProductState,
  (state: ProductState) => state.error)
export const selectProductById = (id: number) => createSelector(
  selectAllProducts,
  (response) => response.find(product => product.id === id)
);
