import {createAction, props} from '@ngrx/store';
import {Product} from "../../../products/data-access/product.model";

export const addToCart = createAction('[Cart] Add to Cart', props<{ product: Product }>());
export const removeFromCart = createAction('[Cart] Remove from Cart', props<{ productId: number }>());
export const updateCartQuantity = createAction('[Cart] Update Quantity', props<{
    productId: number,
    quantity: number
}>());
export const clearCart = createAction('[Cart] Clear Cart');
