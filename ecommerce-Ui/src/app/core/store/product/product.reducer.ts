import {createReducer, on} from '@ngrx/store';
import {ApiResponse, Product} from '../../interfaces/model';
import {getAllProducts, getAllProductsFailure, getAllProductsSuccess} from './product.actions';
export interface ProductState{
  response: ApiResponse<Product> | null;
  loading: boolean;
  error: string | null;
}
export const initialState : ProductState = {
  response: null,
  loading: false,
  error: null
}

export const productReducer = createReducer(initialState,
  on(getAllProducts, (state) => ({
  ...state,
  loading: true})),
  on(getAllProductsSuccess,(state, {response}) => ({
    ...state,
    response: response,
    loading: false
  })),
  on(getAllProductsFailure,(state,{error})=>({
  ...state,
  loading: false,
  error: error}))
);
