import {createFeatureSelector, createSelector} from '@ngrx/store';
import {CartState} from './cart.reducer';


export const selectCartState = createFeatureSelector<CartState>('carts')
export const selectCartItems = createSelector(selectCartState,(state: CartState) => state.cart || [])
export const selectCartLoading = createSelector(selectCartState, (state: CartState) => state.loading)
export const selectCartError = createSelector(selectCartState, (state: CartState) => state.error)
