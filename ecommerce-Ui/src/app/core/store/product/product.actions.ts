import {createAction, props} from '@ngrx/store';
import {ApiResponse, Product} from '../../interfaces/model';

export const getAllProducts = createAction('[Product] Get All Products',props<{page: number, size: number}>());
export const getAllProductsSuccess = createAction('[Product] Get All Products Success',props<{response : ApiResponse<Product>}>());
export const getAllProductsFailure = createAction('[Product] Get All Products Failure', props<{error: string}>());
