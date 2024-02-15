import { ThemeProvider } from "@mui/material";
import "./App.css";

//pages ?

import BSRouter from "./Routers/BSRouter";

import { QueryClientProvider, QueryClient } from "react-query";
import { ReactQueryDevtools } from "react-query/devtools";

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={theme}>
        <BSRouter />
      </ThemeProvider>
      {/* <ReactQueryDevtools isOpen={false} position={"bottom-right"} /> */}
    </QueryClientProvider>
  );
}

export default App;
