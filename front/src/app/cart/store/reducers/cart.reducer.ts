import { createReducer, on } from '@ngrx/store';
import {Product} from "../../../products/data-access/product.model";
import {addToCart, clearCart, removeFromCart, updateCartQuantity} from "../actions/cart.action";

export interface CartItem {
    product: Product;
    quantity: number;
}

export interface CartState {
    items: CartItem[];
}

const initialState: CartState = {
    items: []
};

export const cartReducer = createReducer(
    initialState,
    on(addToCart, (state, { product }) => {
        const existingItem = state.items.find(item => item.product.id === product.id);
        if (existingItem) {
            return {
                ...state,
                items: state.items.map(item =>
                    item.product.id === product.id
                        ? { ...item, quantity: item.quantity + 1 }
                        : item
                )
            };
        }
        return { ...state, items: [...state.items, { product, quantity: 1 }] };
    }),
    on(removeFromCart, (state, { productId }) => ({
        ...state,
        items: state.items.filter(item => item.product.id !== productId)
    })),
    on(updateCartQuantity, (state, { productId, quantity }) => ({
        ...state,
        items: state.items.map(item =>
            item.product.id === productId ? { ...item, quantity } : item
        )
    })),
    on(clearCart, state => ({ ...state, items: [] }))
);
