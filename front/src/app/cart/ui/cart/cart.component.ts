import {Component} from '@angular/core';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {CartItem, CartState} from "../../store/reducers/cart.reducer";
import {selectCartItems, selectTotalAmount} from "../../store/selectors/cart.selector";
import {clearCart, removeFromCart, updateCartQuantity} from "../../store/actions/cart.action";
import {AsyncPipe, NgForOf} from "@angular/common";
import {CardModule} from "primeng/card";
import {FormsModule} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {ButtonDirective} from "primeng/button";

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.scss'],
    standalone: true,
    imports: [
        AsyncPipe,
        CardModule,
        FormsModule,
        InputNumberModule,
        NgForOf,
        ButtonDirective
    ]
})
export class CartComponent {
    cartItems$: Observable<CartItem[]> = this.store.select(selectCartItems);
    totalAmount$: Observable<number> = this.store.select(selectTotalAmount);

    constructor(private store: Store<CartState>) {
    }

    onRemoveItem(productId: number) {
        this.store.dispatch(removeFromCart({productId}));
    }

    onQuantityChange(productId: number, quantity: number) {
        this.store.dispatch(updateCartQuantity({productId, quantity}));
    }

    onClearCart() {
        this.store.dispatch(clearCart());
    }
}
