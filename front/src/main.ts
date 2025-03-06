import {enableProdMode, importProvidersFrom} from "@angular/core";

import {registerLocaleData} from "@angular/common";
import {provideHttpClient, withInterceptorsFromDi,} from "@angular/common/http";
import localeFr from "@angular/common/locales/fr";
import {bootstrapApplication, BrowserModule} from "@angular/platform-browser";
import {provideAnimations} from "@angular/platform-browser/animations";
import {provideRouter} from "@angular/router";
import {APP_ROUTES} from "app/app.routes";
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService} from "primeng/dynamicdialog";
import {AppComponent} from "./app/app.component";
import {environment} from "./environments/environment";
import {provideStore} from "@ngrx/store";
import {cartReducer} from "./app/cart/store/reducers/cart.reducer";
import {provideStoreDevtools} from "@ngrx/store-devtools";

if (environment.production) {
    enableProdMode();
}

bootstrapApplication(AppComponent, {
    providers: [
        importProvidersFrom(BrowserModule),
        provideHttpClient(
            withInterceptorsFromDi(),
        ),
        provideAnimations(),
        provideRouter(APP_ROUTES),
        ConfirmationService,
        MessageService,
        DialogService,
        provideStore({cart: cartReducer}),
        provideStoreDevtools({
            maxAge: 25,
            logOnly: environment.production,
        }),
    ],
}).catch((err) => console.log(err));

registerLocaleData(localeFr, "fr-FR");
