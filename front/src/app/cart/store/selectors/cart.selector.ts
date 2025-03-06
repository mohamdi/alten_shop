import {createFeatureSelector, createSelector} from '@ngrx/store';
import {CartState} from "../reducers/cart.reducer";

const selectCart = createFeatureSelector<CartState>('cart');

export const selectCartItems = createSelector(selectCart, state => state.items);

export const selectTotalAmount = createSelector(selectCart, state =>
    state.items.reduce((total, item) => total + item.product.price * item.quantity, 0)
);

export const selectTotalQuantity = createSelector(selectCart, state =>
    state.items.reduce((total, item) => total + 1, 0)
);

export const selectProductInCart = (productId: number) =>
    createSelector(selectCartItems, items => items.findLast(item => item.product.id === productId));
