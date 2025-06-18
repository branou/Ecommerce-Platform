import {createAction, props} from '@ngrx/store';
import {CartItem, Product} from '../../interfaces/model';

export const addToCart = createAction('[Cart] Add To Cart',props<{product:Product,quantity:number}>());
export const addToCartSuccess = createAction('[Cart] Add To Cart Success');
export const addToCartFailure = createAction('[Cart] Add To Cart Failure',props<{error:string}>())

export const getCart = createAction('[Cart] Get All Cart items',props<{cartId:number}>())
export const getCartSuccess = createAction('[Cart] Get All Cart items Success',props<{cart:CartItem[]}>())
export const getCartFailure = createAction('[Cart] Get All Cart items Failure',props<{error:string}>())

export const removeFromCart = createAction('[Cart] Remove Item From Cart',props<{productId:number}>())
export const removeFromCartSuccess = createAction(
  '[Cart] Remove Item From Cart Success',
  props<{ productId: number }>()
);
export const removeFromCartFailure = createAction('[Cart] Remove Item From Cart Failure',props<{error:string}>())
