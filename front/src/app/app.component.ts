import {Component, inject, OnDestroy,} from "@angular/core";
import {RouterModule} from "@angular/router";
import {SplitterModule} from 'primeng/splitter';
import {ToolbarModule} from 'primeng/toolbar';
import {PanelMenuComponent} from "./shared/ui/panel-menu/panel-menu.component";
import {OverlayModule} from "primeng/overlay";
import {SidebarModule} from "primeng/sidebar";
import {CartSidebarComponent} from "./cart/ui/cart-sidebar/cart-sidebar.component";
import {CartState} from "./cart/store/reducers/cart.reducer";
import {Store} from "@ngrx/store";
import {selectTotalAmount, selectTotalQuantity} from "./cart/store/selectors/cart.selector";
import {NgIf} from "@angular/common";
import {BadgeModule} from "primeng/badge";
import {Subscription} from "rxjs";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.scss"],
    standalone: true,
    imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, OverlayModule, SidebarModule, CartSidebarComponent, NgIf, BadgeModule],
})
export class AppComponent implements OnDestroy {
    title = "ALTEN SHOP";
    showCartDrawer = false;
    private readonly store = inject(Store<CartState>);
    private _totalQuantity$: Subscription;
    private _totalAmount$: Subscription;
    totalQuantity: number = 0
    totalAmount: number = 0;

    constructor() {
        this._totalQuantity$ = this.store.select(selectTotalQuantity).pipe().subscribe(total => {
            this.totalQuantity = total;
        })
        this._totalAmount$ = this.store.select(selectTotalAmount).pipe().subscribe(amount => {
            this.totalAmount = amount;
        })
    }

    ngOnDestroy(): void {
        this._totalQuantity$.unsubscribe();
        this._totalAmount$.unsubscribe();
    }

    toggleCartDrawer() {
        this.showCartDrawer = !this.showCartDrawer;
    }


}

