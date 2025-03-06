import {Component, inject, Input, OnDestroy, OnInit} from '@angular/core';
import {Product} from "../../data-access/product.model";
import {CardModule} from "primeng/card";
import {RatingModule} from "primeng/rating";
import {CurrencyPipe, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {TagModule} from "primeng/tag";
import {ButtonDirective} from "primeng/button";
import {Store} from "@ngrx/store";
import {CartItem, CartState} from "../../../cart/store/reducers/cart.reducer";
import {addToCart, removeFromCart, updateCartQuantity} from "../../../cart/store/actions/cart.action";
import {Subscription} from "rxjs";
import {selectProductInCart} from "../../../cart/store/selectors/cart.selector";
import {InputNumberModule} from "primeng/inputnumber";

@Component({
    selector: 'app-product-card',
    standalone: true,
    templateUrl: './product-card.component.html',
    imports: [
        CardModule,
        RatingModule,
        FormsModule,
        TagModule,
        CurrencyPipe,
        ButtonDirective,
        NgIf,
        InputNumberModule
    ],
    styleUrl: './product-card.component.scss'
})
export class ProductCardComponent implements OnInit, OnDestroy {

    @Input({required: true})
    product!: Product;

    private readonly store = inject(Store<CartState>);

    private _cartProduct$!: Subscription;
    cartProduct: CartItem | undefined;

    ngOnInit(): void {
        this._cartProduct$ = this.store.select(selectProductInCart(this.product.id)).subscribe(product => {
            this.cartProduct = product
        })
    }

    addToCart(product: Product) {
        this.store.dispatch(addToCart({product: product}));
    }

    getSeverity(product: Product) {
        switch (product.inventoryStatus) {
            case 'INSTOCK':
                return 'success';

            case 'LOWSTOCK':
                return 'warning';

            case 'OUTOFSTOCK':
                return 'danger';

            default:
                return undefined;
        }
    }

    ngOnDestroy(): void {
        this._cartProduct$.unsubscribe();
    }

    onQuantityChange(productId: number, quantity: number) {
        this.store.dispatch(updateCartQuantity({productId, quantity}));
    }

    onRemove() {
        this.store.dispatch(removeFromCart({productId: this.product.id}));
    }
}
