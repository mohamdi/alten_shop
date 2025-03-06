import {Component, model} from '@angular/core';
import {SidebarModule} from "primeng/sidebar";
import {ButtonDirective} from "primeng/button";
import {InputNumberModule} from "primeng/inputnumber";
import {FormsModule} from "@angular/forms";
import {CartComponent} from "../cart/cart.component";

@Component({
    selector: 'app-cart-sidebar',
    templateUrl: './cart-sidebar.component.html',
    styleUrls: ['./cart-sidebar.component.scss'],
    standalone: true,
    imports: [
        SidebarModule,
        ButtonDirective,
        InputNumberModule,
        FormsModule,
        CartComponent
    ]
})
export class CartSidebarComponent {
    displayCart = model(false);

    constructor() {
    }

    toggleCart() {
        this.displayCart.update(currentValue => !currentValue);
    }

}
