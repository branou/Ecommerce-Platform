import {CartItem} from '../../interfaces/model';
import {createReducer, on} from '@ngrx/store';
import {
  addToCart,
  addToCartFailure,
  addToCartSuccess,
  getCart,
  getCartFailure,
  getCartSuccess,
  removeFromCart, removeFromCartFailure, removeFromCartSuccess
} from './cart.actions';

export interface CartState{
  cart: CartItem[] | null,
  loading: boolean,
  error: string | null
}
export const initialCartState : CartState = {
  cart: null,
  loading: false,
  error: null
}
export const cartReducer = createReducer(initialCartState,
  on(getCart,(state)=>({...state,leading : true})),
  on(getCartSuccess,(state, {cart})=>({
    ...state,
    cart:cart,
    loading:false
  })),
  on(getCartFailure,(state, {error})=>({
  ...state,
  loading:false,
  error:error
})),
  on(addToCart,(state)=>({
    ...state,loading:true
  })),
  on(addToCartSuccess,(state)=>({
    ...state,loading:false
  })),
  on(addToCartFailure,(state, {error})=>({
    ...state,loading:false,error:error
  })),
  on(removeFromCart,(state)=>({...state,loading:true})),
  on(removeFromCartSuccess, (state, { productId }) => ({
    ...state,
    loading: false,
    cart: state.cart ? state.cart.filter(item => item.product.id !== productId) : null
  })),
  on(removeFromCartFailure,(state,{error})=>({...state,error:error}))
)
